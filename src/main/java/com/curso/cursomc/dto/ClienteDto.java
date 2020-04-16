package com.curso.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.cursomc.domain.Categoria;
import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDto {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfouCnpj;
	private Integer tipo;
	
	public ClienteDto(Integer id,
			@NotEmpty(message = "Preenchimento obrigatório") @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres") String nome,
			@NotEmpty(message = "Preenchimento obrigatório") @Email(message = "email inválido") String email,
			@NotEmpty(message = "Preenchimento obrigatório") String cpfouCnpj, Integer tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfouCnpj = cpfouCnpj;
		this.tipo = tipo;
	}
	public String getCpfouCnpj() {
		return cpfouCnpj;
	}
	public void setCpfouCnpj(String cpfouCnpj) {
		this.cpfouCnpj = cpfouCnpj;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public ClienteDto() {

	}
	public ClienteDto(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email  = obj.getEmail();
		
	}
	
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
	public ClienteDto(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
