package com.oragentalent5.mercadolivre.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Email
	private String login;
	
	@NotEmpty
	@Length(min = 6)
	private String senha;
	@NotNull
	private LocalDateTime instante;

	public Usuario(@NotEmpty @Email String login, @NotEmpty @Min(6) ClearPassword password,
			@NotNull @Future LocalDateTime instante) {
		super();
		this.login = login;
		this.senha = password.hash();
		this.instante = instante;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getInstante() {
		return instante;
	}
	
	

}