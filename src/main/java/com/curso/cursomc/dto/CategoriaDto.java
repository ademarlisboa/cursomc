package com.curso.cursomc.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.Produto;

public class CategoriaDto {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80,message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	} 
	public CategoriaDto(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}
	public CategoriaDto() {

	}
	
}
