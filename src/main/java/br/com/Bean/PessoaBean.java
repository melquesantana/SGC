package br.com.Bean;
import java.io.Serializable;
 
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
 
import br.com.dao.PessoaDAO;
import br.com.modelo.Pessoa;
 
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	 


	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
@PostConstruct // serve para quando o sistema rodar ele ja mostra o que tem no postconstruct
	public void listar() {
		try {

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.ListarPessoas();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar a pessoa");
			erro.printStackTrace();
		}
	}

	public void novo() {
		pessoa = new Pessoa();

	}
//metodo salvar pessoa
	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.salvar(pessoa);//salva a pessoa
			pessoa = new Pessoa();// instancia a pessoa
			pessoas=pessoaDAO.ListarPessoas(); //lista as pessoas
		 
			Messages.addGlobalInfo("Pessoa Salva com Sucesso"); //mensagem na tela
			 
 
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}
	//excluir pessoa
	public void excluir(ActionEvent evento){
		try{
	pessoa =	(Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
	PessoaDAO pessoaDAO = new PessoaDAO();
	pessoaDAO.excluir(pessoa);//exclui a pessoa
	pessoas=pessoaDAO.ListarPessoas();
		Messages.addGlobalInfo("Pessoa Removida com Sucesso" );
	 
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar remover a pessoa");
			erro.printStackTrace();
			
		}
	}
		//editar pessoa
	public void editar(ActionEvent evento){
		
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
	//pega a pessoa selecionada no xhtml
	}
}
