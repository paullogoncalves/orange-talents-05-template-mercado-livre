package com.oragentalent5.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oragentalent5.mercadolivre.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
}
