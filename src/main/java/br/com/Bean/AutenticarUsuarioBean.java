package br.com.Bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.dao.UsuarioDAO;
import br.com.modelo.Pessoa;
import br.com.modelo.Usuario;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name = "autenticarUsuarioBean")
public class AutenticarUsuarioBean implements Serializable {

	private Usuario usuarioLogado;
	private Pessoa pessoa;
	private Usuario usuario;
	//private Usuario alterarUsuario; // recuperar usurio para alterar senha

	 

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();

	}

	public void autenticarLogin() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioLogado = usuarioDAO.autenticar(usuario.getCpf(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;

			}
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogado);
			// Messages.addGlobalInfo("Olá Seja Bem Vindo ao SGC" );

			// System.out.println("entrando...");
			Faces.redirect("./paginas/principal.xhtml");
			// return "principal.xhtml";
			// ?faces-redirect=true
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());

		}
	}
	 public void abrirPDF() throws IOException, InterruptedException{
		 Process pro = Runtime.getRuntime().exec("cmd.exe /c  C:/Users/administrador/Desktop/manualdousuario.pdf");
		 pro.waitFor();
		// java.awt.Desktop.getDesktop().open( new File( "manualdousuario.pdf") );
		  
	 }


	public String sair() {
		usuarioLogado = null;
		return "/paginas/login.xhtml?faces-redirect=true";

	}
	

}
