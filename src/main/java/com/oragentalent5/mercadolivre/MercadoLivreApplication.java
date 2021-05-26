package com.oragentalent5.mercadolivre;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@SpringBootApplication
public class MercadoLivreApplication implements CommandLineRunner {
	@Autowired
	private UsuarioRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario user = new Usuario("paulo@gmail.com", "1234567", LocalDateTime.now());
		
		repo.save(user);
		
		
		
	}

}
