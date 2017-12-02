package br.com.gerarTabela;

import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;

public class GerarTabela {
	@Test
@Ignore
		public void gerarTabela(){
		Persistence.createEntityManagerFactory("exemploPU");
		}
			

	
}
