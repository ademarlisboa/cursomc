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
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.dto.ClienteDto;
import com.curso.cursomc.dto.ClienteNewDto;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.services.exceptions.DataIntegrityException;
import com.curso.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " tipo: " + Cliente.class.getName()));
		
		
	}

	public Cliente update(Cliente obj) {
		Cliente newobj = buscar(obj.getId());
		updateData(newobj,obj);
		return repo.save(obj);
	}
	private void updateData(Cliente newobj,Cliente obj) {
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente que possui produtos");
			
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page,Integer linesPerPage, String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	public Cliente fromDTO(ClienteNewDto objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),objDto.getCpfouCnpj(),objDto.getTipo());
	}
	
	public Cliente fromDTO(ClienteDto objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),objDto.getCpfouCnpj(),objDto.getTipo());
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}
