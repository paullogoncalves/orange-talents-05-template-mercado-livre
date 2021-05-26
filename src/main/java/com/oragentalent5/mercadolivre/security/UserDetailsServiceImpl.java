package com.oragentalent5.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oragentalent5.mercadolivre.domain.Usuario;
import com.oragentalent5.mercadolivre.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario user = repo.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException(login);
		}
		
		return new UserSecurity(user.getId(), user.getLogin(), user.getSenha());
	}

}
