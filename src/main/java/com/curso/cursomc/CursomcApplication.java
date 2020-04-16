package com.curso.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.curso.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Categoria cat3 = new Categoria(null,"banheiro");
		Categoria cat4 = new Categoria(null,"cozinha");
		
		Categoria cat5 = new Categoria(null,"vestuário");
		Categoria cat6 = new Categoria(null,"jardim");
		
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado estado1 = new Estado(null,"Minas Gerais");
		Estado estado2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade (null,"Uberlândia",estado1);
		Cidade c2 = new Cidade (null,"São Paulo",estado2);
		Cidade c3 = new Cidade (null,"Campinas",estado2);
		
		estado1.getCidades().addAll(Arrays.asList(c1));
		estado2.getCidades().addAll(Arrays.asList(c2,c3));
		
		repoEstado.saveAll(Arrays.asList(estado1,estado2));
		repoCidade.saveAll(Arrays.asList(c1,c2,c3));
		
		repo.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6));
		repoProd.saveAll(Arrays.asList(p1,p2,p3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2736323","93838393"));
		
		Endereco end1 = new Endereco(null,"Rua flores","300","apto 303","jardim","38220834",cli1,c1);
		
		Endereco end2 = new Endereco(null,"av matos","105","sala 800","centro","38777012",cli1,c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		repoCliente.saveAll(Arrays.asList(cli1));
		repoEnd.saveAll(Arrays.asList(end1,end2));
		
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
