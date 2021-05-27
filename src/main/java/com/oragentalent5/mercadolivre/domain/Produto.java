package com.oragentalent5.mercadolivre.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.oragentalent5.mercadolivre.dto.CaracteristicasFormDTO;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private BigDecimal valor;

	private int quantidade;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	private String descricao;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Usuario usuario;

	private LocalDateTime instante;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, int quantidade, String descricao, Categoria categoria,
			Usuario usuario, @Size(min = 3) @Valid Collection<CaracteristicasFormDTO> caracteristicas,
			LocalDateTime instante) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.instante = instante;
		this.caracteristicas
				.addAll(caracteristicas.stream().map(cara -> cara.toEntity(this)).collect(Collectors.toSet()));
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
		
	}

	public boolean pertenceAoUsuario(Usuario usuarioRequest) {
		return this.usuario.equals(usuarioRequest);
		
	}

}
