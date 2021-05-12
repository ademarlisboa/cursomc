package com.curso.cursomc.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String senha;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CredenciaisDTO() {
		super();
	}
	public CredenciaisDTO(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
