package br.com.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.JPAUtil.JpaUtil;

@SuppressWarnings("serial")
public class DaoGenerico<Entidade> implements Serializable {
	// dao para salvar qualquer entidade
	public void salvar(Entidade entidade) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();

			manager.merge(entidade);
			tx.commit();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			if (entidade != null) {
				tx.rollback();
			}
			throw erro;
		} finally {
			manager.close();
			JpaUtil.close();
		}

	}

	public void excluir(Entidade entidade) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		try {
			manager.getTransaction().begin();
			Object c = manager.merge(entidade);
			manager.remove(c);
			manager.getTransaction().commit();
		} catch (RuntimeException erro) {
			if (entidade != null) {
				tx.rollback();
			}
			throw erro;
		} finally {
			manager.close();
			JpaUtil.close();
		}
	}

}
