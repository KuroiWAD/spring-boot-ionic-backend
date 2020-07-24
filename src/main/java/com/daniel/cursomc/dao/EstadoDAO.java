package com.daniel.cursomc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cursomc.domain.Estado;

@Repository
public interface EstadoDAO extends JpaRepository<Estado, Integer>{
	

}