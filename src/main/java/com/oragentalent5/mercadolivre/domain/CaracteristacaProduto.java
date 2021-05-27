package com.oragentalent5.mercadolivre.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CaracteristacaProduto {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@ManyToOne
	private Produto produto;

	public CaracteristacaProduto() {

	}

	public CaracteristacaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
