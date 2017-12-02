package br.com.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_setor")
public class Setor extends ModeloGeral implements Serializable {
	
	@Column(length = 30, nullable = false, unique = true)
	private String nome;
@Column(name="nome_setor")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	 

}
