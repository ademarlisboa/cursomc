package com.curso.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.dto.CategoriaDto;
import com.curso.cursomc.repositories.CategoriaRepository;
import com.curso.cursomc.services.exceptions.DataIntegrityException;
import com.curso.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " tipo: " + Categoria.class.getName()));
		
		
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newobj = buscar(obj.getId());
		updateData(newobj,obj);
		return repo.save(obj);
	}
	
	private void updateData(Categoria newobj,Categoria obj) {
		newobj.setNome(obj.getNome());
	}
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
			
		}

		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage, String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	public Categoria fromDTO(CategoriaDto objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
}
