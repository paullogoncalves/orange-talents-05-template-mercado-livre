package com.oragentalent5.mercadolivre.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nota;
	
	private String titulo;
	
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;

	public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

}
