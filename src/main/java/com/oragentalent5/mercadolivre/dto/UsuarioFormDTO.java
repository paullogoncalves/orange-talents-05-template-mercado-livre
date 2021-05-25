package com.oragentalent5.mercadolivre.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oragentalent5.mercadolivre.domain.ClearPassword;
import com.oragentalent5.mercadolivre.domain.Usuario;

public class UsuarioFormDTO {

	@NotBlank
	@NotNull
	@Email
	private String login;
	@NotBlank
	@NotNull
	@Min(6)
	private String senha;
	
	public UsuarioFormDTO(@NotBlank @NotNull @Email String login, @NotBlank @NotNull @Min(6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario toEntity() {
		return new Usuario(this.login, new ClearPassword(this.senha), LocalDateTime.now());
	}
}
