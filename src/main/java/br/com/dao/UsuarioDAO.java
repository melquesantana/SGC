package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.shiro.crypto.hash.SimpleHash;

import br.com.JPAUtil.JpaUtil;
import br.com.modelo.Usuario;

@SuppressWarnings("serial")
public class UsuarioDAO extends DaoGenerico<Usuario> {
	public List<Usuario> ListarUsuarios() {

		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();
		try {
			Query query = manager.createQuery("from Usuario");
			@SuppressWarnings("unchecked")
			List<Usuario> resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			 manager.close();
			 JpaUtil.close();
		}
	}

	public Usuario autenticar(String cpf, String senha) {
		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();
		try {
			Query query = manager.createQuery("select u from Usuario u  where u.cpf = :cpf and u.senha = :senha");
			
			query.setParameter("cpf", cpf);
			SimpleHash simpleHash = new SimpleHash("md5",senha);
			query.setParameter("senha", simpleHash.toHex());
			Usuario lista = (Usuario) query.getSingleResult();
			return lista;
		} catch (NoResultException erro) {
			return null;
		}

		finally {
			manager.close();
			
			JpaUtil.close();
		}
	}

	public List<Usuario> listarNome(String codigo_pessoa) {

		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();
		try {
			Query query = manager.createQuery("from Pessoa where codigo_pessoa=:codigo_pessoa");
			query.setParameter("codigo_pessoa", codigo_pessoa);
			@SuppressWarnings("unchecked")
			List<Usuario> resultado = query.getResultList();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;

		} finally {
			manager.close();
			JpaUtil.close();
			
		}
	}

}
