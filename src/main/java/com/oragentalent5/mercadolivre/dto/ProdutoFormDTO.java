package com.oragentalent5.mercadolivre.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.oragentalent5.mercadolivre.domain.Categoria;
import com.oragentalent5.mercadolivre.domain.Produto;
import com.oragentalent5.mercadolivre.domain.Usuario;

public class ProdutoFormDTO {

	@NotEmpty(message = "Campo Obrigatório")
	private String nome;

	@Positive
	@NotNull
	private BigDecimal valor;

	@PositiveOrZero
	private int quantidade;

	@Size(min = 3)
	@Valid
	private List<CaracteristicasFormDTO> caracteristicas = new ArrayList<>();

	@NotEmpty
	@Length(max = 1000)
	private String descricao;

	@NotNull
	private Long idCategoria;

	public ProdutoFormDTO(@NotEmpty String nome, @Positive @NotNull BigDecimal valor, @PositiveOrZero int quantidade,
			List<CaracteristicasFormDTO> caracteristicas, @NotEmpty @Length(max = 1000) String descricao,
			@NotBlank Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas.addAll(caracteristicas);
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public List<CaracteristicasFormDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicasFormDTO> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Produto toEntity(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		return new Produto(nome, valor, quantidade, descricao, categoria, usuario,caracteristicas, LocalDateTime.now());
	}
	
	public Set<String> buscarCaracteristicaIgual() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for(CaracteristicasFormDTO caracteristica: caracteristicas) {
			String nome = caracteristica.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}	
		}
		return resultados;
	}

}
