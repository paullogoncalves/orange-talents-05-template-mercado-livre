package com.oragentalent5.mercadolivre.resources;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Pergunta;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.PerguntaFormDTO;
import com.oragentalent5.mercadolivre.repositories.PerguntaRepository;
import com.oragentalent5.mercadolivre.repositories.ProdutoRepository;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private UsuarioRepository UsuarioRepo;
	
	@Autowired
	private PerguntaRepository perguntaRepo;
	
//	@Autowired
//	private EnvioEmailWork emailWork;
	
	@Transactional
	@PostMapping("/{id}/perguntas")
	public ResponseEntity<Pergunta> create(@Valid @RequestBody PerguntaFormDTO dto, @PathVariable("id") Long id) {
		Usuario user = UsuarioRepo.findByLogin("jhondoe@gmail.com");
		Produto produto = produtoRepo.findById(id).get();
		Pergunta pergunta = dto.toEntity(produto, user);
		perguntaRepo.save(pergunta);
		
		//emailWork.sendPerguntaEmail(pergunta);
		return ResponseEntity.ok().body(pergunta);
	}

}
