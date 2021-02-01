package com.daniel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.cursomc.dao.CategoriaDAO;
import com.daniel.cursomc.domain.Categoria;
import com.daniel.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO dao;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
}
