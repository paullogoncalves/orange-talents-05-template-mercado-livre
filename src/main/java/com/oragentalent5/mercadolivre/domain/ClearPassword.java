package com.oragentalent5.mercadolivre.domain;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ClearPassword {
	
	private String senha;

	public ClearPassword(@NotBlank @Length(min =6) String senha) {
		this.senha = senha;
	}
	
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	

}
