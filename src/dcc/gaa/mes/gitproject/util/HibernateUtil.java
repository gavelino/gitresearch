package dcc.gaa.mes.gitproject.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	 
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
