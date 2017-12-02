package br.com.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.dao.ReservaDAO;
import br.com.modelo.Reserva;
import br.com.modelo.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ListaReservaAmbienteBean implements Serializable {

	//private Usuario usuarioSessao = new Usuario();
	AutenticarUsuarioBean aUsuarioBean = new AutenticarUsuarioBean();
	private ReservaDAO reservaDAO;
	private Reserva reserva;
	private List<Reserva> listaDeReserva;

	@PostConstruct
	public void listar() {
		//setUsuarioSessao(
		
			aUsuarioBean.setUsuarioLogado(
					(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
		try {
			reservaDAO = new ReservaDAO();
			listaDeReserva = reservaDAO.ListarReservas();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar listar os ambientes reservados");
			erro.printStackTrace();
		}
	}

	public void excluirReserva(ActionEvent evento) {
		try {
			reserva = (Reserva) evento.getComponent().getAttributes().get("devolverAmbiente");
			ReservaDAO reservaDAO = new ReservaDAO();
			//setUsuarioSessao(
			aUsuarioBean.setUsuarioLogado(
					(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
			reservaDAO.excluir(reserva);
			listaDeReserva = reservaDAO.ListarReservas();
			Messages.addGlobalInfo("ambiente devolvido com Sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar devolver o ambiente");
			erro.printStackTrace();

		}
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getListaDeReserva() {
		return listaDeReserva;
	}

	public void setListaDeReserva(List<Reserva> listaDeReserva) {
		this.listaDeReserva = listaDeReserva;
	}

	 

	public boolean usuarioSessaoReserva() {
		return true;
	}

}
