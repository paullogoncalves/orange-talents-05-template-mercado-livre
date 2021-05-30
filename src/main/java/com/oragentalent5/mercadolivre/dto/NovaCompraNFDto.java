package com.oragentalent5.mercadolivre.dto;

public class NovaCompraNFDto {

	private Long idCompra;
	private Long idComprador;
	public NovaCompraNFDto(Long idCompra, Long idComprador) {
		super();
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NovaCompraNFDto [idCompra=");
		builder.append(idCompra);
		builder.append(", idComprador=");
		builder.append(idComprador);
		builder.append("]");
		return builder.toString();
	}
	
	
}
