package com.oragentalent5.mercadolivre.dto;

public class RankingDto {

	private Long idCompra;
	private Long idVendedor;
	public RankingDto(Long idCompra, Long idVendedor) {
		super();
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RankingDto [idCompra=");
		builder.append(idCompra);
		builder.append(", idVendedor=");
		builder.append(idVendedor);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
