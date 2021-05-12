package com.curso.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.security.UserSS;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Cliente cli = this.clienteRepository.findByEmail(username);
		if(cli == null) {
			throw new UsernameNotFoundException("Usuário não encontrado " + username);
		}
		
		return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
	}

}
