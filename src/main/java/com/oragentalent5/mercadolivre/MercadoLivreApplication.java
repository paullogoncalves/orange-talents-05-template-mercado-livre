package com.oragentalent5.mercadolivre;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oragentalent5.mercadolivre.domain.Categoria;
import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.email.EnvioEmailWork;
import com.oragentalent5.mercadolivre.email.MockEmailWork;
import com.oragentalent5.mercadolivre.repositories.CategoriaRepository;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@SpringBootApplication
public class MercadoLivreApplication implements CommandLineRunner {
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoRepo;

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario user = new Usuario("paulo@gmail.com", pe.encode("1234567"), LocalDateTime.now());
		Usuario user1 = new Usuario("jhondoe@gmail.com", pe.encode("12345678"), LocalDateTime.now());
		Usuario user2 = new Usuario("joaosembraco@gmail.com", pe.encode("123456789"), LocalDateTime.now());
		repo.saveAll(Arrays.asList(user, user1, user2));
		
		Categoria categoria = new Categoria("Eletronicos");
		categoRepo.save(categoria);

	}
	
	@Bean
	public EnvioEmailWork envioEmailWork() {
		return new MockEmailWork();
	}

}
