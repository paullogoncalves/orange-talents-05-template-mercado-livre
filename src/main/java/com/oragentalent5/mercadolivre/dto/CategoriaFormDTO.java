package com.oragentalent5.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.oragentalent5.mercadolivre.domain.Categoria;

public class CategoriaFormDTO {
	
	@NotEmpty
	private String nome;
	
	@Positive
	private Long idCategoriaMae;

	public CategoriaFormDTO(@NotEmpty String nome, @NotNull Long idCategoria) {
		this.nome = nome;
		this.idCategoriaMae = idCategoria;
	}
	
	public Categoria toEntity(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			categoria.setMae(categoriaMae);
		}
		return categoria ;
	}

	
}
