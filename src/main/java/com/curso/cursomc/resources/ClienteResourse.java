package com.curso.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.dto.ClienteDto;
import com.curso.cursomc.dto.ClienteNewDto;
import com.curso.cursomc.services.ClienteService;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResourse {
	@Autowired
	private ClienteService service;
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity <Cliente> find(@PathVariable Integer id)  {
		
		
			Cliente obj = service.buscar(id);
			List<Cliente> lista = new ArrayList<Cliente>();
			lista.add(obj);
			return ResponseEntity.ok().body(obj);
		
			
		}
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<Cliente>> findAll()  {
			List<Cliente> obj = service.findAll();
			return ResponseEntity.ok().body(obj);
		}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto objDto){
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
	}
	

	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity <Page<ClienteDto>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction)  {
		
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDto> listDto = list.map(obj -> new ClienteDto(obj));
			
			return ResponseEntity.ok().body(listDto);
	}
	
	}
