package com.curso.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.Pedido;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.repositories.PedidoRepository;
import com.curso.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " tipo: " + Pedido.class.getName()));
		
		
	}
}
