package com.oragentalent5.mercadolivre.resources;

import java.time.LocalDateTime;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.UsuarioFormDTO;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/login")
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder enc;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Usuario> login(@Valid @RequestBody UsuarioFormDTO dto) {
		Usuario usuario = new Usuario(dto.getLogin(), enc.encode(dto.getSenha()), LocalDateTime.now());
		usuarioRepo.save(usuario);
		
		return ResponseEntity.ok().build();
	}

}
