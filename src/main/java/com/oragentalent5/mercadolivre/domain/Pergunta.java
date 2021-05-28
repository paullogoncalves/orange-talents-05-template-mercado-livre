package com.oragentalent5.mercadolivre.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	private LocalDateTime instante;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	@NotNull
	@ManyToOne
	private Produto produto;

	public Pergunta(@NotBlank String titulo, LocalDateTime instante, @NotNull Usuario usuario,
			@NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.instante = instante;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pergunta [titulo=");
		builder.append(titulo);
		builder.append(", instante=");
		builder.append(instante);
		builder.append(", usuario=");
		builder.append(usuario.getLogin());
		builder.append(", produto=");
		builder.append(produto.getUsuario().getLogin());
		builder.append("]");
		return builder.toString();
	}

	
}
