package com.oragentalent5.mercadolivre.resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.ProdutoFormDTO;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;
import com.oragentalent5.mercadolivre.resources.validation.ProibeCaracteristicasIguais;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository repo;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicasIguais());
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ProdutoFormDTO dto) {
		Usuario user = repo.findByLogin("paulo@gmail.com");
		Produto produto = dto.toEntity(manager, user);
		manager.persist(produto);
		return ResponseEntity.ok().build();
	}

}
