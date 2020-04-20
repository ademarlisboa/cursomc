package com.curso.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.cursomc.domain.Pedido;
import com.curso.cursomc.services.PedidoService;


@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService service;
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity <Pedido> find(@PathVariable Integer id)  {
		
		
			Pedido obj = service.buscar(id);
			List<Pedido> lista = new ArrayList<Pedido>();
			lista.add(obj);
			return ResponseEntity.ok().body(obj);
		
			
		}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
		//	Pedido obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
	}
		
	}
