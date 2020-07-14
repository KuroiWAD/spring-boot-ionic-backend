package com.daniel.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.cursomc.dao.CategoriaDAO;
import com.daniel.cursomc.domain.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO dao;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElse(null);
	}

}
