package com.oragentalent5.mercadolivre.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

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

	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();

	@OneToMany(mappedBy = "produto")
	private Set<OpiniaoProduto> opinioes = new HashSet<>();

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
		this.getCaracteristicas()
				.addAll(caracteristicas.stream().map(cara -> cara.toEntity(this)).collect(Collectors.toSet()));
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Opinioes getOpinioes() {
		return new Opinioes(this.opinioes);
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);

	}

	public boolean pertenceAoUsuario(Usuario usuarioRequest) {
		return this.usuario.equals(usuarioRequest);
	}

	public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [nome=");
		builder.append(nome);
		builder.append(", usuario=");
		builder.append(usuario.getLogin());
		builder.append("]");
		return builder.toString();
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

	public boolean abateDoEstoque(int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para o abatimento" + quantidade);
		if (quantidade <= this.quantidade) {
			this.quantidade -= quantidade;
			return true;
		}
		return false;
		
	}

	

}
