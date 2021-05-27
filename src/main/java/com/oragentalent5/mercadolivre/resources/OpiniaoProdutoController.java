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

import com.oragentalent5.mercadolivre.domain.OpiniaoProduto;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.dto.OpiniaoProdutoFormDTO;
import com.oragentalent5.mercadolivre.repositories.OpiniaoProdutoRepository;
import com.oragentalent5.mercadolivre.repositories.ProdutoRepository;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class OpiniaoProdutoController {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private UsuarioRepository Usuariorepo;
	
	@Autowired
	private OpiniaoProdutoRepository opiniaoRepository;
	
	@Transactional
	@PostMapping("/{id}/opiniao")
	public ResponseEntity<OpiniaoProduto> create(@Valid @RequestBody OpiniaoProdutoFormDTO dto, @PathVariable("id") Long id) {
		Usuario user = Usuariorepo.findByLogin("paulo@gmail.com");
		Produto produto = produtoRepo.findById(id).get();
		OpiniaoProduto opiniaoProduto = dto.toEntity(produto, user);
		
		opiniaoRepository.save(opiniaoProduto);
		return ResponseEntity.ok().body(opiniaoProduto);
	}

}
