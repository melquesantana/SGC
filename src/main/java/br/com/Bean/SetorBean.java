package br.com.Bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.dao.SetorDAO;
import br.com.modelo.Setor;

@SuppressWarnings("serial")
@ManagedBean 
@ViewScoped
public class SetorBean implements Serializable{
	private Setor setor = new Setor();
	private SetorDAO SetorDAO = new SetorDAO();
	private List<Setor>setores;
	 

	
	public void novo(){
		setor = new Setor();
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	//metodo salvar setor
	public void salvar() {
		try {
			SetorDAO SetorDAO = new SetorDAO();
            SetorDAO.salvar(setor);// salvar setor
			setor = new Setor();
		    setores =  SetorDAO.ListarSetores();
	 
			Messages.addGlobalInfo("setor salvo com sucesso!");
			//System.out.println("bloco salvo  salvo com sucesso!");
		} catch (RuntimeException erro) {
// mensagem de erro para o usuario 
			Messages.addGlobalError("ocorreu um erro ao tentar salvar o setor");
			erro.printStackTrace();	 
		}
		}
	 @PostConstruct
	public void listar() {

		try {
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.ListarSetores();
       Messages.addGlobalInfo("lista todos os setores");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar salvar o setor");
			erro.printStackTrace();
		}

	}
	
	 
	public void excluir(ActionEvent evento) {
		try {
			setor =   (Setor) evento.getComponent().getAttributes().get("setorSelecionado");
			
			SetorDAO.excluir(setor);
			setores= SetorDAO.ListarSetores();
			Messages.addGlobalInfo("setor excluido com sucesso.");
			//System.out.println("bloco excluido");
			//Messages.addGlobalInfo("o bloco excluido é " + bloco.getIdentificacao());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um  erro ao tentar remover  o setor");
			//System.out.println("erro ao tentar remover o bloco");
			erro.printStackTrace();

		}
	}
	public void editar(ActionEvent evento) {
		setor =   (Setor) evento.getComponent().getAttributes().get("setorSelecionado");
//Messages.addGlobalInfo("Bloco editado com Sucesso");
		//System.out.println("bloco selecionado");
	}
}
	 
 

