package br.com.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "tb_ambiente")
public class Ambiente extends ModeloGeral implements Serializable{
	
	@Column(length = 40, nullable = false)
	private String nome;
	
	@Column(length = 10, nullable = false)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name="codigo_setor",nullable=false)	
	private Setor setor;


	 

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
