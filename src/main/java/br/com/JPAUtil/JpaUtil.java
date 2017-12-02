package br.com.JPAUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//classe para conexao com o banco de dados
public class JpaUtil {
	private static EntityManagerFactory factory;
	static {
// nome da persistence unit no persitence.xml
		factory = Persistence.createEntityManagerFactory("exemploPU");
	}
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
		//Connection con = new ConnectionFactory().getConnection();
	}
	
	public static void close() {
		//factory.close();
	}
	// coneão jdbc par ao jasper
	public Connection getConnection() {
	    try {
	        return DriverManager.getConnection(
	"jdbc:mysql://localhost/db_sgc", "root", "root");
	        // banco de ados conectado e a senha e usurio
	    } catch (SQLException e) {
	        throw new RuntimeException(e);}
	    }
	 
	

}


