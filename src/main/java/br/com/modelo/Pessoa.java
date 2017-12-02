package br.com.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
// uma tabela no banco de dados chamada tb_pessoa
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends ModeloGeral implements Serializable {
	// coluna de tamanho 40 e nao pode ser nula
	@Column(length = 40, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false)
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	// public static List<Pessoa> createCars(int i) {
	// TODO Auto-generated method stub
	// return null;
	// }

}
