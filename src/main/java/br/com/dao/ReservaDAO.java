package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.JPAUtil.JpaUtil;
import br.com.modelo.Reserva;
import br.com.modelo.Ambiente;

@SuppressWarnings("serial")
public class ReservaDAO extends DaoGenerico<Reserva> {

	public void agendar(Reserva reserva) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.persist(reserva);
			tx.commit();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			if (reserva != null) {
				tx.rollback();
			}
			throw erro;
		} finally {
			 manager.close();
			  JpaUtil.close();
		}
	}

	/*
	 * public List<Reserva> ListarReservasPorUsuario(Usuario usuario) {
	 * EntityManager manager = JpaUtil.getEntityManager();
	 * 
	 * @SuppressWarnings("unused") EntityTransaction tx =
	 * manager.getTransaction(); try { Query query =
	 * manager.createQuery("from Reserva where codigo_usuario =:codigo_usuario"
	 * ); query.setParameter("codigo_usuario", usuario);
	 * 
	 * @SuppressWarnings("unchecked") List<Reserva> resultado =
	 * query.getResultList(); return resultado; } catch (RuntimeException erro)
	 * { erro.printStackTrace();
	 * 
	 * } finally {
	 * 
	 * } return ListarReservas();
	 * 
	 * }
	 */
	public List<Reserva> ListarReservas() {
		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();
		try {
			Query query = manager.createQuery("from Reserva");
			@SuppressWarnings("unchecked")
			List<Reserva> resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();

		} finally {
manager.close();
JpaUtil.close();
		}
		return ListarReservas();

	}

	/*
	 * public Reserva listarNome(Usuario usuario) {
	 * 
	 * EntityManager manager = JpaUtil.getEntityManager();
	 * 
	 * @SuppressWarnings("unused") EntityTransaction tx =
	 * manager.getTransaction(); try { Query query =
	 * manager.createQuery("from Reserva where codigo_usuario =:codigo_usuario"
	 * ); query.setParameter("codigo_usuario", usuario);
	 * 
	 * @SuppressWarnings("unchecked") Reserva resultado = (Reserva)
	 * query.getSingleResult(); return resultado; } catch (RuntimeException
	 * erro) { throw erro;
	 * 
	 * } finally { // manager.close(); // JpaUtil.close(); } }
	 */
	public boolean jaReservado(Ambiente ambiente) {
		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();
		try {
			Query query = manager.createQuery("from Reserva where ambiente_id=:ambiente_id");
			query.setParameter("ambiente_id", ambiente);
			@SuppressWarnings("unchecked")
			List<Reserva> rSala = query.getResultList();
			if (rSala.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			 manager.close();
			 JpaUtil.close();
		}
	}

	public void deletar(Reserva reserva) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			manager.getTransaction().begin();
			Reserva r = manager.merge(reserva);
			manager.remove(r);
			manager.getTransaction().commit();
		} catch (RuntimeException erro) {
			if (reserva != null) {
				tx.rollback();
			}
			throw erro;
		} finally {
			manager.close();
			JpaUtil.close();
		}
	}
}
