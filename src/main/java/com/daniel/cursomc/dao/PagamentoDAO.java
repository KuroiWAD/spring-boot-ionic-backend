package com.daniel.cursomc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daniel.cursomc.domain.Pagamento;

@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer>{
	

}
