package br.com.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_reserva")
public class Reserva extends ModeloGeral implements Serializable {
	
	@Column(nullable = false)
	private Date data;
	@Column(nullable = false)
	
	//private Date dataDevolucao;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	 
	@ManyToOne
	@JoinColumn(name="codigo_usuario",nullable=false)	
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name="ambiente_id",nullable=false)	
	private Ambiente ambiente;
	 
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ambiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}
	 
	 
}
