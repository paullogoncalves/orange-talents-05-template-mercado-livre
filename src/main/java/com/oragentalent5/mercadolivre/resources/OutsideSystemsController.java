package com.oragentalent5.mercadolivre.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oragentalent5.mercadolivre.dto.NovaCompraNFDto;
import com.oragentalent5.mercadolivre.dto.RankingDto;

@RestController
public class OutsideSystemsController {

	
	@PostMapping(value = "/notas-fiscais")
	public void criarNota(@ RequestBody NovaCompraNFDto novaCompraNFDto) throws InterruptedException {
		System.out.println("Criando nota para: " + novaCompraNFDto);
	
	}
	
	@PostMapping(value = "/ranking")
	public void criandoRanking(@RequestBody RankingDto dto) {
		System.out.println("Criando nota para: " + dto);

	}
}
