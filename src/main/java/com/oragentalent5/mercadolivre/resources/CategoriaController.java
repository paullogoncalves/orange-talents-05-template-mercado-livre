package com.oragentalent5.mercadolivre.resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Categoria;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.CategoriaFormDTO;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Categoria> login(@Valid @RequestBody CategoriaFormDTO dto) {
		Categoria categoria = dto.toEntity(manager);
		
		manager.persist(categoria);
		return ResponseEntity.ok().build();
	}

}
