package com.oragentalent5.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

import com.oragentalent5.mercadolivre.domain.Opinioes;
import com.oragentalent5.mercadolivre.domain.Produto;

public class DetalheProdutoDTO {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Set<CaracteristicaDTO> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double notasMedia;

	public DetalheProdutoDTO(Produto prod) {
		this.nome = prod.getNome();
		this.descricao = prod.getDescricao();
		this.preco = prod.getValor();
		this.caracteristicas = prod.getCaracteristicas().stream().map(cara -> new CaracteristicaDTO(cara))
				.collect(Collectors.toSet());
		this.linksImagens = prod.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = prod.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

		Opinioes opinioes = prod.getOpinioes();
		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.notasMedia = opinioes.media();

	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Set<CaracteristicaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getNotasMedia() {
		return notasMedia;
	}

}
