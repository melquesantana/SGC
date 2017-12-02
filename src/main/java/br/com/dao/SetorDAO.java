package br.com.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.JPAUtil.JpaUtil;
import br.com.modelo.Setor;
 

@SuppressWarnings("serial")
public class SetorDAO extends DaoGenerico<Setor>{
	
	
	@SuppressWarnings("unused")
	public List<Setor>ListarSetores(){
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();		
	try {
		Query query = manager.createQuery("from Setor");
		@SuppressWarnings("unchecked")
		List<Setor>resultado = query.getResultList();
	return	resultado;
	} catch (RuntimeException erro) {
		throw erro;
		
	}	
	finally{
		manager.close();
		JpaUtil.close();
	}
	}

	}
