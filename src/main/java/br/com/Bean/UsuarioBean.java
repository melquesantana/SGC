package br.com.Bean;
import java.io.Serializable;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.mysql.jdbc.Connection;

import br.com.JPAUtil.JpaUtil;
import br.com.dao.PessoaDAO;
import br.com.dao.UsuarioDAO;
 
import br.com.modelo.Pessoa;
import br.com.modelo.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

 
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.ListarUsuarios();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.ListarPessoas();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			SimpleHash hash = new SimpleHash("md5" , usuario.getSenhaSCriptografia());
			usuario.setSenha(hash.toHex());
			
			usuarioDAO.salvar(usuario);;
			
			usuario = new Usuario();
			usuarios = usuarioDAO.ListarUsuarios();
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.ListarPessoas();
			
			Messages.addGlobalInfo("Usuário salvo com sucesso");
			 
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		}
	}
	public void excluir(ActionEvent evento){
		try {
			usuario =   (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		 
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			 
			usuarioDAO.excluir(usuario);
			 usuarios=usuarioDAO.ListarUsuarios();
		 
			Messages.addGlobalInfo("usuario excluido com sucesso.");
			//System.out.println("usuario excluido");
			//Messages.addGlobalInfo("o usuario excluido é " +usuario.getPessoa().getNome());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao tentar remover  o usuario");
			//System.out.println("erro ao tentar remover a cliente");
			erro.printStackTrace();

		}
	}
	public void editar(ActionEvent evento){
		


		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			  
			PessoaDAO pessoaDAO =  new PessoaDAO();
			pessoas=pessoaDAO.ListarPessoas();
			 
		 
			
		 
			 
		//	System.out.println("usuario selecionado");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar Selecionar o usuario");
			erro.printStackTrace();
			//System.out.println("erro ao selecionar usuario");
		}}
	 
	 public void relatorioUsuario() {
	    	try{
	    	  Connection  conn = (Connection) new  JpaUtil().getConnection();
	    	 
	    		String local = Faces.getRealPath("/relatorios/usuarios.jasper");
	   
	    	//HashMap  param = new HashMap();
	    
	    	  Map<String, Object> parametros = new HashMap<>();
	     
	        //JasperPrint print = JasperFillManager.fillReport("webapp/pages/relatorios/usuarios.jasper", param, conn);
	    	JasperPrint relatorio = JasperFillManager.fillReport(local, parametros, conn);
	        //JasperViewer jw = new JasperViewer(print);
	        //jw.setVisible(true);
	        //JasperExportManager.exportReportToPdfFile(print,"pages/relatorios/usuarios.pdf");
	        //JasperPrintManager.printReport(print, true);
	    	JasperPrintManager.printReport(relatorio, true);
	    	}catch (JRException e){
	    		e.printStackTrace();
	    		
	    	}
	    	 
	    }
	 public void relatorioAmbiente() {
	    	try{
	    	  Connection  conn = (Connection) new  JpaUtil().getConnection();
	    	 
	    		String local = Faces.getRealPath("/relatorios/ambientes.jasper");
	   
	    	//HashMap  param = new HashMap();
	    
	    	  Map<String, Object> parametros = new HashMap<>();
	     
	        //JasperPrint print = JasperFillManager.fillReport("webapp/pages/relatorios/usuarios.jasper", param, conn);
	    	JasperPrint relatorio = JasperFillManager.fillReport(local, parametros, conn);
	        //JasperViewer jw = new JasperViewer(print);
	        //jw.setVisible(true);
	        //JasperExportManager.exportReportToPdfFile(print,"pages/relatorios/usuarios.pdf");
	        //JasperPrintManager.printReport(print, true);
	    	JasperPrintManager.printReport(relatorio, true);
	    	}catch (JRException e){
	    		e.printStackTrace();
	    		
	    	}
	    	 
	    }
	 public void relatorioPessoa() {
	    	try{
	    	  Connection  conn = (Connection) new  JpaUtil().getConnection();
	    	 
	    		String local = Faces.getRealPath("/relatorios/pessoas.jasper");
	   
	    	//HashMap  param = new HashMap();
	    
	    	  Map<String, Object> parametros = new HashMap<>();
	     
	        //JasperPrint print = JasperFillManager.fillReport("webapp/pages/relatorios/usuarios.jasper", param, conn);
	    	JasperPrint relatorio = JasperFillManager.fillReport(local, parametros, conn);
	        //JasperViewer jw = new JasperViewer(print);
	        //jw.setVisible(true);
	        //JasperExportManager.exportReportToPdfFile(print,"pages/relatorios/usuarios.pdf");
	        //JasperPrintManager.printReport(print, true);
	    	JasperPrintManager.printReport(relatorio, true);
	    	}catch (JRException e){
	    		e.printStackTrace();
	    		
	    	}
	    	 
	    }
	 public void relatorioReserva() {
	    	try{
	    	  Connection  conn = (Connection) new  JpaUtil().getConnection();
	    	 
	    		String local = Faces.getRealPath("/relatorios/reservas.jasper");
	   
	    	//HashMap  param = new HashMap();
	    
	    	  Map<String, Object> parametros = new HashMap<>();
	     
	        //JasperPrint print = JasperFillManager.fillReport("webapp/pages/relatorios/usuarios.jasper", param, conn);
	    	JasperPrint relatorio = JasperFillManager.fillReport(local, parametros, conn);
	        //JasperViewer jw = new JasperViewer(print);
	        //jw.setVisible(true);
	        //JasperExportManager.exportReportToPdfFile(print,"pages/relatorios/usuarios.pdf");
	        //JasperPrintManager.printReport(print, true);
	    	JasperPrintManager.printReport(relatorio, true);
	    	}catch (JRException e){
	    		e.printStackTrace();
	    		
	    	}
	    	 
	    }
	 public void relatorioSetore() {
	    	try{
	    	  Connection  conn = (Connection) new  JpaUtil().getConnection();
	    	 
	    		String local = Faces.getRealPath("/relatorios/setores.jasper");
	   
	    	//HashMap  param = new HashMap();
	    
	    	  Map<String, Object> parametros = new HashMap<>();
	     
	        //JasperPrint print = JasperFillManager.fillReport("webapp/pages/relatorios/usuarios.jasper", param, conn);
	    	JasperPrint relatorio = JasperFillManager.fillReport(local, parametros, conn);
	        //JasperViewer jw = new JasperViewer(print);
	        //jw.setVisible(true);
	        //JasperExportManager.exportReportToPdfFile(print,"pages/relatorios/usuarios.pdf");
	        //JasperPrintManager.printReport(print, true);
	    	JasperPrintManager.printReport(relatorio, true);
	    	}catch (JRException e){
	    		e.printStackTrace();
	    		
	    	}
	    	 
	    }
	 }