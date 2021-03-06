package com.curso.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.bytebuddy.asm.Advice.This;
@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();
	
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido ip : itens) {
			soma = soma + ip.getSubtotal();
		}
		return soma;
	}
	
	
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	
	
	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	

	
	

	public Pedido() {
		
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco) {
		super();
		this.id = id;
		this.instante = instante;
		
		this.cliente = cliente;
		this.enderecoDeEntrega = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return enderecoDeEntrega;
	}

	public void setEndereco(Endereco endereco) {
		this.enderecoDeEntrega = endereco;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		NumberFormat nf =  NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		
		builder.append("Pedido numero: ");
		builder.append(this.getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(this.getInstante()));
		builder.append(", Cliente: ");
		builder.append(this.getCliente().getNome());
		builder.append(", Situação de Pagamento: ");
		builder.append(this.getPagamento().getEstado().getDescricao());
		builder.append("\n Detalhes: \n");
		for (ItemPedido ip : this.getItens()) {
			builder.append(ip.toString());
		}
		builder.append(" Valor Total: ");
		builder.append(nf.format(this.getValorTotal()));
		builder.append(" \n");
		return builder.toString();
	}
	

}
