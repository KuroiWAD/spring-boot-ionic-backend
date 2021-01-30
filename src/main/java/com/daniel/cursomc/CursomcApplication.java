package com.daniel.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.daniel.cursomc.dao.CategoriaDAO;
import com.daniel.cursomc.dao.CidadeDAO;
import com.daniel.cursomc.dao.ClienteDAO;
import com.daniel.cursomc.dao.EnderecoDAO;
import com.daniel.cursomc.dao.EstadoDAO;
import com.daniel.cursomc.dao.PagamentoDAO;
import com.daniel.cursomc.dao.PedidoDAO;
import com.daniel.cursomc.dao.ProdutoDAO;
import com.daniel.cursomc.domain.Categoria;
import com.daniel.cursomc.domain.Cidade;
import com.daniel.cursomc.domain.Cliente;
import com.daniel.cursomc.domain.Endereco;
import com.daniel.cursomc.domain.Estado;
import com.daniel.cursomc.domain.Pagamento;
import com.daniel.cursomc.domain.PagamentoComBoleto;
import com.daniel.cursomc.domain.PagamentoComCartao;
import com.daniel.cursomc.domain.Pedido;
import com.daniel.cursomc.domain.Produto;
import com.daniel.cursomc.domain.enums.EstadoPagamento;
import com.daniel.cursomc.domain.enums.TipoCliente;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private ProdutoDAO produtoDAO;
	@Autowired
	private EstadoDAO estadoDAO;
	@Autowired
	private CidadeDAO cidadeDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private EnderecoDAO enderecoDAO;
	@Autowired
	private PagamentoDAO pagamentoDAO;
	@Autowired
	private PedidoDAO pedidoDAO;

	
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
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaDAO.saveAll(Arrays.asList(cat1,cat2));
		produtoDAO.saveAll(Arrays.asList(p1,p2,p3));
		
		//ORGANIZAÇÃO
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlânida", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoDAO.saveAll(Arrays.asList(est1,est2));
		cidadeDAO.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Daniel Fernandes", "daniel@gmail.com", "996762069", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("18996762069", "1833235988"));

		Endereco e1 = new Endereco(null, "Rua Anhumas", "271", "Casa", "Prudenciana", "19803210", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Vicente Negri", "173", "Casa", "Operaria", "19804150", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteDAO.saveAll(Arrays.asList(cli1));
		enderecoDAO.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/12/2020 10:46"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("27/01/2020 22:45"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2020 00:00"));
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoDAO.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoDAO.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}

}
