package com.daniel.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.cursomc.dao.ItemPedidoDAO;
import com.daniel.cursomc.dao.PagamentoDAO;
import com.daniel.cursomc.dao.PedidoDAO;
import com.daniel.cursomc.domain.ItemPedido;
import com.daniel.cursomc.domain.PagamentoComBoleto;
import com.daniel.cursomc.domain.Pedido;
import com.daniel.cursomc.domain.enums.EstadoPagamento;
import com.daniel.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDAO dao;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoDAO pagamentoDAO;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoDAO itemPedidoDAO;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = dao.save(obj);
		pagamentoDAO.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoDAO.saveAll(obj.getItens());
		return obj;
	}
}
