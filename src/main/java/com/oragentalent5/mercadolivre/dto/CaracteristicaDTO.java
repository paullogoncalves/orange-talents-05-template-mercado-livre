package com.oragentalent5.mercadolivre.dto;

import com.oragentalent5.mercadolivre.domain.CaracteristicaProduto;

public class CaracteristicaDTO {

	private String nome;
	private String descricao;

	public CaracteristicaDTO(CaracteristicaProduto cara) {
		this.nome = cara.getNome();
		this.descricao = cara.getDescricao();

	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
