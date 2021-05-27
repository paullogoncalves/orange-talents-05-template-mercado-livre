package com.oragentalent5.mercadolivre.resources;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.UploadFake;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.ImagemFormDTO;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class EnviarImagemController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UploadFake uploadFake;

	@Transactional
	@PostMapping("/{id}/imagens")
	public ResponseEntity<Void> login(@Valid ImagemFormDTO imagemDTO, @PathVariable("id") Long id) {
		Set<String> links = uploadFake.send(imagemDTO.getImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaImagens(links);
		
		Usuario usuario = usuarioRepo.findByLogin("paulo@gmail.com");
		
		 if (!produto.pertenceAoUsuario(usuario)) {
			 throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		 }
		
		manager.merge(produto);
		return ResponseEntity.ok().build();

	}

}
