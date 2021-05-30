package com.oragentalent5.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oragentalent5.mercadolivre.domain.Compra;
import com.oragentalent5.mercadolivre.domain.RetornoGatwayPagamento;
import com.oragentalent5.mercadolivre.domain.Transacao;
import com.oragentalent5.mercadolivre.enums.StatusRetornoPagamento;

public class RetornoPagSeguroDTO implements RetornoGatwayPagamento{

	@NotBlank
	private String idTransacao;
	@NotNull
	private StatusRetornoPagamento status;

	public RetornoPagSeguroDTO(String idTransacao, StatusRetornoPagamento status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

	public StatusRetornoPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusRetornoPagamento status) {
		this.status = status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetornoPagSeguroDTO [idTransacao=");
		builder.append(idTransacao);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
}
