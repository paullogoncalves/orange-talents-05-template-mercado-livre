package com.oragentalent5.mercadolivre.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.oragentalent5.mercadolivre.dto.RetornoPagSeguroDTO;
import com.oragentalent5.mercadolivre.enums.GatwayPagamento;

import io.jsonwebtoken.lang.Assert;

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

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "compra")
	private Set<Transacao> transacoes = new HashSet<>();
	
	public Compra() {
		
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

	public void addTransacao(RetornoGatwayPagamento dto) {
		Transacao novaTransacao = dto.toTransacao(this);
		
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "ja existe essa transação");
		
		Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra ja foi concluida com sucesso ");
		
		this.transacoes.add(novaTransacao);
		
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao:: concluidaComSucesso).collect(Collectors.toSet());
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}
	
	
	
	
	
}
