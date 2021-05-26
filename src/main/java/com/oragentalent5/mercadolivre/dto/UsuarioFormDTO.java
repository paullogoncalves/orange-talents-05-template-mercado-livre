package com.oragentalent5.mercadolivre.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.resources.validation.UniqueValue;

public class UsuarioFormDTO {

	@NotEmpty
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	@NotEmpty
	@Min(6)
	private String senha;
	
	public UsuarioFormDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioFormDTO(@NotEmpty @Email String login, @NotEmpty @Min(6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario toEntity() {
		return new Usuario(this.login, this.senha, LocalDateTime.now());
	}
}
