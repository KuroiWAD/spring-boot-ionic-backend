package com.daniel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.cursomc.dao.ClienteDAO;
import com.daniel.cursomc.domain.Cliente;
import com.daniel.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO dao;
	
	public Cliente find(Integer id) {
		 Optional<Cliente> obj = dao.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
