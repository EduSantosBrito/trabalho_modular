package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.edu.granbery.trabalhomodular.model.Pessoa;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;
import br.edu.granbery.trabalhomodular.util.JPAUtil;

public class PessoaController {
	
	private static final String SELECT_BY_DOCUMENTO = "SELECT c FROM %s as c WHERE c.documento = :documento";
	private GenericCrud<Pessoa, Integer> gci = CrudFactory.buildCrudFor(Pessoa.class);
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public Pessoa create(Pessoa Pessoa) {
		return gci.create(Pessoa);
	}

	public Pessoa update(Pessoa Pessoa) {
		return gci.update(Pessoa);
	}
	
	public Pessoa find(Integer id) {
		return gci.find(id);
	}
	
	public List<Pessoa> findAll() {
		return gci.findAll();
	}
	
	public void delete(Pessoa pessoa) {
		gci.delete(pessoa);
	}
	
	public Pessoa findByDocumento(String documento) {
		entityManager.getTransaction().begin();
		Pessoa pessoa = null;
		try {
			pessoa = (Pessoa) entityManager.createQuery(String.format(SELECT_BY_DOCUMENTO, Pessoa.class.getSimpleName())).setParameter("documento", documento).getSingleResult();
		} catch (NoResultException e) {
			entityManager.getTransaction().commit();
			return pessoa;
		}
		entityManager.getTransaction().commit();
		return pessoa;
	}
}
