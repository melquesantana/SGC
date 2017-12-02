package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

 
import br.com.JPAUtil.JpaUtil;
import br.com.modelo.Ambiente;
 
 
 

@SuppressWarnings("serial")
public class AmbienteDAO extends DaoGenerico<Ambiente>{
	
	public List<Ambiente>ListarAmbiente(){
		EntityManager manager = JpaUtil.getEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction tx = manager.getTransaction();		
	try {
		Query query = manager.createQuery("from Ambiente");
		@SuppressWarnings("unchecked")
		List<Ambiente>resultado = query.getResultList();
		return	resultado;
	} catch (RuntimeException erro) {
		erro.printStackTrace();
		
	}
	finally{
		manager.close();
		JpaUtil.close();
	}
	return ListarAmbiente();
	 
 
	}
	
	
}
