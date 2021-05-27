package com.oragentalent5.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oragentalent5.mercadolivre.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
	
}
