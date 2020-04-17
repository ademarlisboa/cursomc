package com.curso.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.Produto;
import com.curso.cursomc.repositories.CategoriaRepository;
import com.curso.cursomc.repositories.ProdutoRepository;
import com.curso.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	public Produto buscar(Integer id) throws ObjectNotFoundException {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " tipo: " + Produto.class.getName()));
		
		
	}
	
	public Page<Produto> findPage(Integer page,Integer linesPerPage, String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Page <Produto> search(String nome,List<Integer> ids,Integer page,Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		
		List<Categoria> categorias = categoriaRepo.findAll();
		return repo.search(nome,categorias,pageRequest);
	}
	
}