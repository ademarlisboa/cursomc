package com.curso.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Jurídica");

	private int codigo;
	private String descricao;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}
}