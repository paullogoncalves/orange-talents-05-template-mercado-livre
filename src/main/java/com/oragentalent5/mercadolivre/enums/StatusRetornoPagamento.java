package com.oragentalent5.mercadolivre.enums;

public enum StatusRetornoPagamento {

	SUCESSO, ERROR;

	public StatusTransacao normaliza() {
		if (this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}

		return StatusTransacao.error;
	}
}
