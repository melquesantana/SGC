package br.com.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.dao.AmbienteDAO;
import br.com.dao.SetorDAO;
import br.com.modelo.Ambiente;
import br.com.modelo.Setor;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class AmbienteBean implements Serializable {
	private Ambiente ambiente;
	private List<Ambiente> ambientes;
	private List<Setor> setores;

	

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Ambiente> getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(List<Ambiente> ambientes) {
		this.ambientes = ambientes;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	

	 

	@PostConstruct
	public void listar() {
		try {
			AmbienteDAO ambienteDAO = new AmbienteDAO();
			ambientes = ambienteDAO.ListarAmbiente();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar listar as ambientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ambiente = new Ambiente();
			SetorDAO setorDAO =  new SetorDAO();
			setores = setorDAO.ListarSetores();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao gerar um novo ambiente");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AmbienteDAO ambienteDAO = new AmbienteDAO();

			ambienteDAO.salvar(ambiente);
			ambiente = new Ambiente();

			SetorDAO setorDAO =  new SetorDAO();
			setores = setorDAO.ListarSetores();

			ambientes = ambienteDAO.ListarAmbiente();
			Messages.addGlobalInfo("ambiente salvo com sucesso");
			// System.out.println("sala salva com sucesso.");
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			Messages.addGlobalError("erro ao tentar salvar o ambiente");
			// System.out.println("erro ao tentar salvar uma nova sala");
		}

	}

	public void excluir(ActionEvent evento) {
		try {
			ambiente = (Ambiente) evento.getComponent().getAttributes().get("ambienteSelecionado");
			AmbienteDAO ambienteDAO = new AmbienteDAO();
			ambienteDAO.excluir(ambiente);// excluir ambiente

			ambientes = ambienteDAO.ListarAmbiente();
			Messages.addGlobalInfo("ambiente excluido com sucesso.");
			// System.out.println("sala excluida");
			//Messages.addGlobalInfo("o ambiente excluido é " + ambiente.getNome());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar remover  o ambiente");
			// System.out.println("erro ao tentar remover a sala");
			erro.printStackTrace();
			
		}
	}

	public void editar(ActionEvent evento) {

		try {
			// action eveto pra pegar o objeto selecionado
			ambiente = (Ambiente) evento.getComponent().getAttributes().get("ambienteSelecionado");
			SetorDAO setorDAO= new SetorDAO();
			setores = setorDAO.ListarSetores();

			// System.out.println("sala selecionada");

			// novo();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar Selecionar uma ambiente");
			erro.printStackTrace();
			// System.out.println("erro ao selecionar cidade");
		}
	}

}
