package com.oragentalent5.mercadolivre.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	public Categoria() {
		
	}

	public Categoria(@NotEmpty String nome) {
		this.nome = nome;
	}
	
	public void setMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}
	
	
}
