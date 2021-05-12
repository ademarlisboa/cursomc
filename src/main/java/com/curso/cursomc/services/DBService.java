package com.curso.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.Cidade;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.Endereco;
import com.curso.cursomc.domain.Estado;
import com.curso.cursomc.domain.ItemPedido;
import com.curso.cursomc.domain.Pagamento;
import com.curso.cursomc.domain.PagamentoComBoleto;
import com.curso.cursomc.domain.PagamentoComCartao;
import com.curso.cursomc.domain.Pedido;
import com.curso.cursomc.domain.Produto;
import com.curso.cursomc.domain.enums.EstadoPagamento;
import com.curso.cursomc.domain.enums.Perfil;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.CategoriaRepository;
import com.curso.cursomc.repositories.CidadeRepository;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.repositories.EnderecoRepository;
import com.curso.cursomc.repositories.EstadoRepository;
import com.curso.cursomc.repositories.ItemPedidoRepository;
import com.curso.cursomc.repositories.PagamentoRepository;
import com.curso.cursomc.repositories.PedidoRepository;
import com.curso.cursomc.repositories.ProdutoRepository;


@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository repo;
	

	@Autowired
	private ProdutoRepository repoProd;
	
	@Autowired
	private EstadoRepository repoEstado;
	
	@Autowired
	private CidadeRepository repoCidade;
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private EnderecoRepository repoEnd;
	
	@Autowired
	private PagamentoRepository repoPagto;

	@Autowired
	private PedidoRepository repoPed;
	
	@Autowired
	private ItemPedidoRepository repoItens;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiatetestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Categoria cat3 = new Categoria(null,"banheiro");
		Categoria cat4 = new Categoria(null,"cozinha");
		
		Categoria cat5 = new Categoria(null,"vestuário");
		Categoria cat6 = new Categoria(null,"jardim");
		Categoria cat7 = new Categoria(null,"bem estar");
		
		
		
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		
		
		Produto p4 = new Produto(null,"mesa escritório",200.00);
		Produto p5 = new Produto(null,"toalha",10.00);
		Produto p6 = new Produto(null,"Shampoo",20.00);
		
		
		Produto p7 = new Produto(null,"carro",200.00);
		Produto p8 = new Produto(null,"ventilador",10.00);
		Produto p9 = new Produto(null,"cafeteira",20.00);
		
		Produto p10 = new Produto(null,"pendente",20.00);
		
		Produto p11 = new Produto(null,"creme",20.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		
		cat5.getProdutos().addAll(Arrays.asList(p8));
		
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6)); 
		
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7)); 
		
		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade (null,"Uberlândia",estado1);
		Cidade c2 = new Cidade (null,"São Paulo",estado2);
		Cidade c3 = new Cidade (null,"Campinas",estado2);
		
		estado1.getCidades().addAll(Arrays.asList(c1));
		estado2.getCidades().addAll(Arrays.asList(c2,c3));
		
		repoEstado.saveAll(Arrays.asList(estado1,estado2));
		repoCidade.saveAll(Arrays.asList(c1,c2,c3));
		
		repo.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		repoProd.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","ademar.lisboa@gmail.com","36378912377",TipoCliente.PESSOAFISICA,pe.encode("biriba"));
		cli1.getTelefones().addAll(Arrays.asList("2736323","93838393"));
		cli1.addPerfil(Perfil.ADMIN);
		
		Cliente cli2 = new Cliente(null,"Ana costa","ana.lisboa@gmail.com","83211241515",TipoCliente.PESSOAFISICA,pe.encode("biriba"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("353534534","99002137"));
		
		
		
		Endereco end1 = new Endereco(null,"Rua flores","300","apto 303","jardim","38220834",cli1,c1);
		
		Endereco end2 = new Endereco(null,"av matos","105","sala 800","centro","38777012",cli1,c2);
		
		Endereco end3 = new Endereco(null,"rua claudio m da costa","105","sala 800","centro","38777012",cli2,c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		cli2.getEnderecos().addAll(Arrays.asList(end3));
			
		
		
		repoCliente.saveAll(Arrays.asList(cli1,cli2));
		repoEnd.saveAll(Arrays.asList(end1,end2,end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,end1);
		
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		
		ped1.setPagamento(pagto1);
	

		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);

		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		repoPed.saveAll(Arrays.asList(ped1,ped2));
		
		repoPagto.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		
		
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		
		p2.getItens().addAll(Arrays.asList(ip3));
		
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		repoItens.saveAll(Arrays.asList(ip1,ip2,ip3));
		repo.saveAll(Arrays.asList(cat1,cat2));
	}

}
