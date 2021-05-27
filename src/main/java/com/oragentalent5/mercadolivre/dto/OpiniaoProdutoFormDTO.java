package com.oragentalent5.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.oragentalent5.mercadolivre.domain.OpiniaoProduto;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;

public class OpiniaoProdutoFormDTO {

	@Range(min = 1, max = 5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoProdutoFormDTO(@Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Long usarioId, @NotNull Long produtoId) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public OpiniaoProduto toEntity(Produto produto, Usuario usuario) {
		return new OpiniaoProduto(nota, titulo, descricao, usuario, produto);
	}

}
