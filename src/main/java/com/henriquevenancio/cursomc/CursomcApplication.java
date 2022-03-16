package com.henriquevenancio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.henriquevenancio.cursomc.domain.Categoria;
import com.henriquevenancio.cursomc.domain.Cidade;
import com.henriquevenancio.cursomc.domain.Cliente;
import com.henriquevenancio.cursomc.domain.Endereco;
import com.henriquevenancio.cursomc.domain.Estado;
import com.henriquevenancio.cursomc.domain.Pagamento;
import com.henriquevenancio.cursomc.domain.PagamentoComBoleto;
import com.henriquevenancio.cursomc.domain.PagamentoComCartao;
import com.henriquevenancio.cursomc.domain.Pedido;
import com.henriquevenancio.cursomc.domain.Produto;
import com.henriquevenancio.cursomc.domain.enums.EstadoPagamento;
import com.henriquevenancio.cursomc.domain.enums.TipoCliente;
import com.henriquevenancio.cursomc.repositories.CategoriaRepository;
import com.henriquevenancio.cursomc.repositories.CidadeRepository;
import com.henriquevenancio.cursomc.repositories.ClienteRepository;
import com.henriquevenancio.cursomc.repositories.EnderecoRepository;
import com.henriquevenancio.cursomc.repositories.EstadoRepository;
import com.henriquevenancio.cursomc.repositories.PagamentoRepository;
import com.henriquevenancio.cursomc.repositories.PedidoRepository;
import com.henriquevenancio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
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
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "0123456789", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("0123456789", "9876543210"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "0123456789", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "0123456789", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		//Máscara de formatação para instanciar uma data.
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("16/03/2022 11:50"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("16/03/2022 11:56"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);				
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("16/03/2022 00:00"), null);				
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		
		
	}

}
