package br.edu.granbery.trabalhomodular.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public class GenericCrudImpl<T, PK extends Serializable> implements GenericCrud<T, PK>{
	
	private static final String SELECT_ALL_QUERY = "SELECT c FROM %s as c";
	private static final String DELETE_FROM_QUERY = "DELETE FROM %s WHERE %s = :id";
	private Class<T> entityClass;
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	
	GenericCrudImpl() {}
	
	public GenericCrudImpl(Class<T> type) {
		entityClass = type;
	}

	public T create(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
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

	public void delete(PK id) {
		entityManager.getTransaction().begin();
		entityManager.createQuery(String.format(DELETE_FROM_QUERY, entityClass.getSimpleName(), "id")).setParameter("id", id).executeUpdate();
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
