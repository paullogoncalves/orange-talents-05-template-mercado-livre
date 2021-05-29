package com.oragentalent5.mercadolivre.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.oragentalent5.mercadolivre.GatwayPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Produto produto;
	
	private int quantidade;
	
	@ManyToOne
	private Usuario usuario;
	
	@Enumerated(EnumType.ORDINAL)
	private GatwayPagamento gatwayPagamento;
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(Produto produto, int quantidade, Usuario usuario, GatwayPagamento gatwayPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gatwayPagamento = gatwayPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public GatwayPagamento getGatwayPagamento() {
		return gatwayPagamento;
	}
	
	
	
	
	
}
