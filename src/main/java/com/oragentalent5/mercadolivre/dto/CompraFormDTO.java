package com.oragentalent5.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.enums.GatwayPagamento;

public class CompraFormDTO {

	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	
	private GatwayPagamento gatwayPagamento;

	public CompraFormDTO(@Positive int quantidade, @NotNull Long idProduto, @NotBlank GatwayPagamento gatwayPagamento) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gatwayPagamento = gatwayPagamento;
	}

	
	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatwayPagamento getGatwayPagamento() {
		return gatwayPagamento;
	}

}
