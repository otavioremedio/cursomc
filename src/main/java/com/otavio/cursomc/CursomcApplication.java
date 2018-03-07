package com.otavio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.otavio.cursomc.domain.Categoria;
import com.otavio.cursomc.domain.Cidade;
import com.otavio.cursomc.domain.Cliente;
import com.otavio.cursomc.domain.Endereco;
import com.otavio.cursomc.domain.Estado;
import com.otavio.cursomc.domain.ItemPedido;
import com.otavio.cursomc.domain.Pagamento;
import com.otavio.cursomc.domain.PagamentoComBoleto;
import com.otavio.cursomc.domain.PagamentoComCartao;
import com.otavio.cursomc.domain.Pedido;
import com.otavio.cursomc.domain.Produto;
import com.otavio.cursomc.domain.enums.EstadoPagamento;
import com.otavio.cursomc.domain.enums.TipoCliente;
import com.otavio.cursomc.repositories.CategoriaRepository;
import com.otavio.cursomc.repositories.CidadeRepository;
import com.otavio.cursomc.repositories.ClienteRepository;
import com.otavio.cursomc.repositories.EnderecoRepository;
import com.otavio.cursomc.repositories.EstadoRepository;
import com.otavio.cursomc.repositories.ItemPedidoRepository;
import com.otavio.cursomc.repositories.PagamentoRepository;
import com.otavio.cursomc.repositories.PedidoRepository;
import com.otavio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedioRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		this.categoriaRepository.save(Arrays.asList(c1,c2));
		this.produtoRepository.save(Arrays.asList(p1,p2, p3));
		
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Ubuerlandia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		this.estadoRepository.save(Arrays.asList(e1,e2));
		this.cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","27363525"));
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av Matos", "3005", "Apto 20", "Centro", "38700834", cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		this.clienteRepository.save(Arrays.asList(cli1));
		this.enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		this.pedioRepository.save(Arrays.asList(ped1, ped2));
		this.pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		this.itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
	}
}
