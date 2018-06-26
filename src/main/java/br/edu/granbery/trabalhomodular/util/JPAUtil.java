package br.edu.granbery.trabalhomodular.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabalhomodular");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
