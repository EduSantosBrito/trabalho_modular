package br.edu.granbery.trabalhomodular.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public class GenericCrudImpl<T, PK extends Serializable> implements GenericCrud<T, PK>{
	
	private static final String SELECT_ALL_QUERY = "SELECT c FROM %s as c";
	private Class<T> entityClass;
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	
	GenericCrudImpl() {}
	
	public GenericCrudImpl(Class<T> type) {
		entityClass = type;
	}

	public T create(T t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		return t;
	}

	public T find(PK id) {
		entityManager.getTransaction().begin();
		T t = entityManager.find(entityClass, id);
		entityManager.getTransaction().commit();
		return t;
	}

	public T update(T t) {
		entityManager.getTransaction().begin();
		T t2 = entityManager.merge(t);
		entityManager.getTransaction().commit();
		return t2;
	}

	public void delete(T t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}
	
	public List<T> findAll() {
		entityManager.getTransaction().begin();
		List<T> listOfT = entityManager.createQuery(String.format(SELECT_ALL_QUERY, entityClass.getSimpleName()), entityClass)
				.getResultList();
		entityManager.getTransaction().commit();
		return listOfT;
	}
	
}
