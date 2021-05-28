package com.oragentalent5.mercadolivre.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oragentalent5.mercadolivre.domain.Pergunta;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;

public class PerguntaFormDTO {

	@NotBlank
	private String titulo;
	
	@Deprecated
	public PerguntaFormDTO() {
	}
	
	public PerguntaFormDTO(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	public Pergunta toEntity(Produto produto, Usuario user) {
		return new Pergunta(titulo, LocalDateTime.now(), user, produto) ;
	}

	public String getTitulo() {
		return titulo;
	}

	
}
