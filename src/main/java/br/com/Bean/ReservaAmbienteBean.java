package br.com.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.dao.ReservaDAO;
import br.com.dao.AmbienteDAO;
import br.com.modelo.Setor;
import br.com.modelo.Reserva;
import br.com.modelo.Ambiente;
import br.com.modelo.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReservaAmbienteBean implements Serializable {
	private Ambiente ambiente;
	private List<Ambiente> ambientes;
	private List<Ambiente> ambienteSemReserva;
	private List<Setor> setors;
	private Ambiente ambienteSelecionado;
	private ReservaDAO reservaDAO;
	private AmbienteDAO ambienteDAO;
	private Reserva reserva;
	private Usuario usuarioSessao = new Usuario();
	private Boolean reservados;
	//private Date dataDaReserva;
	//private Date dataDaDevolucao;

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public List<Ambiente> getAmbientes() {

		return ambientes;
	}

	public void setAmbientes(List<Ambiente> ambientes) {
		this.ambientes = ambientes;
	}


	

	public List<Setor> getSetors() {
		return setors;
	}

	public void setSetors(List<Setor> setors) {
		this.setors = setors;
	}
	 

	public List<Ambiente> getAmbienteSemReserva() {
		return ambienteSemReserva;
	}

	public void setAmbienteSemReserva(List<Ambiente> ambienteSemReserva) {
		this.ambienteSemReserva = ambienteSemReserva;
	}

	public Ambiente getAmbienteSelecionado() {
		return ambienteSelecionado;
	}

	public void setAmbienteSelecionado(Ambiente ambienteSelecionado) {
		this.ambienteSelecionado = ambienteSelecionado;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public Boolean isReservados() {
		return reservados;
	}

	public void setReservados(Boolean reservados) {
		this.reservados = reservados;
	}

	public void salvar() {
		if (reservaDAO.jaReservado(ambienteSelecionado)) {
			Messages.addGlobalInfo("Ambiente ja foi Reservado...");
			listar();
		} else {
			try {
				reservaDAO = new ReservaDAO();
				reserva = new Reserva();
				reserva.setData(new Date());// data da reserva
				//reserva.set(dataDaDevolucao);// data da devolução
				reserva.setAmbiente(ambienteSelecionado);// ambiente selecionado
				reserva.setUsuario(usuarioSessao);// usuario que reservou

				reservaDAO.agendar(reserva);

				Messages.addGlobalInfo("reserva realizada com sucesso");
				// System.out.println("sala salva com sucesso.");
			} catch (RuntimeException erro) {
				Messages.addGlobalError("erro ao tentar reservar o Ambiente");

				// System.out.println("erro ao tentar salvar uma nova sala");
			} finally {
				listar();
			}
		}

	}

	/*
	 * public void deletar() { reservaDAO = new ReservaDAO(); Reserva excluir =
	 * (Reserva) reservaDAO.listarNome(usuarioSessao);
	 * reservaDAO.excluir(excluir); }
	 */
	@PostConstruct
	public void listar() {
		usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		try {
			ambienteDAO = new AmbienteDAO();
			reservaDAO = new ReservaDAO();
			ambientes = ambienteDAO.ListarAmbiente();
			ambienteSemReserva = new ArrayList<Ambiente>();
			List<Ambiente> ambientesDaReserva = new ArrayList<Ambiente>();
			for (Reserva rRreserva : reservaDAO.ListarReservas()) {
				ambientesDaReserva.add(rRreserva.getAmbiente());
			}

			ambienteSemReserva.addAll(juntarAmbientes(ambientes, ambientesDaReserva));

		} catch (RuntimeException erro) {
			Messages.addGlobalError("ocorreu um erro ao tentar listar as ambientes para reservas");
			erro.printStackTrace();
		}
	}

	public void carregarAmbiente(SelectEvent ambiente) {
		ambienteSelecionado = (Ambiente) ambiente.getObject();

		System.out.println("carregou");
		ambiente = (SelectEvent) ambiente.getComponent().getAttributes().get("ambienteSelecionado");
		salvar();
	}

	public List<Ambiente> juntarAmbientes(List<Ambiente> lista1, List<Ambiente> lista2) {
		// criar nova lista
		List<Ambiente> lista3 = new ArrayList<Ambiente>();

		// juntar a lista 1 com a lista 2
		lista3.addAll(lista1);
		lista3.addAll(lista2);

		// remover o que há de comum em ambas
		Ambiente elemento = null;
		for (int i = 0; i < lista3.size(); i++) {
			// obter elemento atual
			elemento = lista3.get(i);
			// verificar se ele existe nas duas listas
			if (lista1.contains(elemento) && lista2.contains(elemento)) {
				// existe, então remove da nova lista
				lista3.remove(i);
				// se um item é removido, evita o incremento do 'for'
				i--;
			}
		}
		// devolver a nova lista
		return lista3;
	}

	
}
