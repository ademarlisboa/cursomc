package com.curso.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {


	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@JsonIgnore
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preço;
	
	public ItemPedido() {
		
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public void setPedido(Pedido p) {
		id.setPedido(p);
		
	}
	
	
	public void setProduto(Produto p) {
		id.setProduto(p);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf =  NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		
		builder.append(getProduto().getNome());
		builder.append(", Qtde: ");
		builder.append(quantidade);
		builder.append(", preço: ");
		builder.append(nf.format(preço));
		builder.append(", Subtotal: ");
		builder.append(nf.format(this.getSubtotal()));
		builder.append(" \n");
		return builder.toString();
	}

	public Double getSubtotal() {
		return (preço - desconto) * quantidade;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreço() {
		return preço;
	}

	public void setPreço(Double preço) {
		this.preço = preço;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ItemPedido(Pedido pedido,Produto produto, Double desconto, Integer quantidade, Double preço) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preço = preço;
	}
	
	
}
