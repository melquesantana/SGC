package br.com.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import br.com.enumeracao.TipoDeUsuario;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_usuario")
public class Usuario extends ModeloGeral implements Serializable {

	@NotEmpty
	@CPF 
	@Column(length = 15, unique = true, nullable = false)
	private String cpf;

	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSCriptografia;
	
public String getSenhaSCriptografia() {
		return senhaSCriptografia;
	}

	public void setSenhaSCriptografia(String senhaSCriptografia) {
		this.senhaSCriptografia = senhaSCriptografia;
	}

	// enumeração do tipo string
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDeUsuario tipoDeUsuario;

	@OneToOne
	@JoinColumn(name = "codigo_pessoa", nullable = false)
	private Pessoa pessoa;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
