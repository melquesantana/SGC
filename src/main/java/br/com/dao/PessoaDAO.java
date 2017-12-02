package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.JPAUtil.JpaUtil;
import br.com.modelo.Pessoa;
 
 

@SuppressWarnings("serial")
public class PessoaDAO extends DaoGenerico<Pessoa>{
	@SuppressWarnings("unused")
	public List<Pessoa>ListarPessoas(){
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();		
	try {
		Query query = manager.createQuery("from Pessoa");
		@SuppressWarnings("unchecked")
		List<Pessoa>resultado = query.getResultList();
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


