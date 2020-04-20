package com.curso.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.cursomc.domain.ItemPedido;
import com.curso.cursomc.domain.PagamentoComBoleto;
import com.curso.cursomc.domain.Pedido;
import com.curso.cursomc.domain.Produto;
import com.curso.cursomc.domain.enums.EstadoPagamento;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.repositories.ItemPedidoRepository;
import com.curso.cursomc.repositories.PagamentoRepository;
import com.curso.cursomc.repositories.PedidoRepository;
import com.curso.cursomc.repositories.ProdutoRepository;
import com.curso.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired 
	private ClienteService clienteService; 
	
	@Autowired 
	private EmailService emailService;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " tipo: " + Pedido.class.getName()));
		
		
	}
	@Transactional
	public @Valid Pedido insert(@Valid Pedido obj) {
		// TODO Auto-generated method stub
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.prencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			Optional<Produto> p = produtoRepository.findById(ip.getProduto().getId());
			ip.setProduto (p.get());
			ip.setPreço( p.get().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
