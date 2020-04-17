package com.curso.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.cursomc.domain.Produto;
import com.curso.cursomc.dto.CategoriaDto;
import com.curso.cursomc.dto.ProdutoDto;
import com.curso.cursomc.resources.utils.URL;
import com.curso.cursomc.services.ProdutoService;


@RestController
@RequestMapping(value="/Produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity <Produto> find(@PathVariable Integer id)  {
		
		
			Produto obj = service.buscar(id);
			List<Produto> lista = new ArrayList<Produto>();
			lista.add(obj);
			return ResponseEntity.ok().body(obj);
		
			
		}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDto>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction)  {
			List<Integer> ids = URL.decodeIntlist(categorias);
			String nomeDecoded = URL.decodeParam(nome);
			Page<Produto> list = service.search(nomeDecoded , ids, page, linesPerPage, orderBy, direction);
			Page<ProdutoDto> listDto = list.map(obj -> new ProdutoDto(obj));
			return ResponseEntity.ok().body(listDto);
	}
		
	}
