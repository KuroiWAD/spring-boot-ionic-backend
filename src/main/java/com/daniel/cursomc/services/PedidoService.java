package com.daniel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.cursomc.dao.PedidoDAO;
import com.daniel.cursomc.domain.Pedido;
import com.daniel.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoDAO dao;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
