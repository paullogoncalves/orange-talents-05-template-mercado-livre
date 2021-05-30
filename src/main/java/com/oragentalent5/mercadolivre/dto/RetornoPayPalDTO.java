package com.oragentalent5.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import com.oragentalent5.mercadolivre.domain.Compra;
import com.oragentalent5.mercadolivre.domain.RetornoGatwayPagamento;
import com.oragentalent5.mercadolivre.domain.Transacao;
import com.oragentalent5.mercadolivre.enums.StatusTransacao;

public class RetornoPayPalDTO implements RetornoGatwayPagamento {

	@NotBlank
	private String idTransacao;
	@Range(min = 0, max = 1)
	private int status;

	public RetornoPayPalDTO(String idTransacao, int status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}



	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status == 0 ? StatusTransacao.error 
				: StatusTransacao.sucesso, idTransacao, compra);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RetornoPagSeguroDTO [idTransacao=");
		builder.append(idTransacao);
		builder.append(", status=");
		builder.append(getStatus());
		builder.append("]");
		return builder.toString();
	}

	public int getStatus() {
		return status;
	}
	
	
}
