package com.oragentalent5.mercadolivre.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.dto.DetalheProdutoDTO;
import com.oragentalent5.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheProdutoDTO> create(@PathVariable("id") Long id) {
		Produto produto = produtoRepo.findById(id).get();
		
		DetalheProdutoDTO dto = new DetalheProdutoDTO(produto);
		
	
		return ResponseEntity.ok().body(dto);
	}
	
	

}
