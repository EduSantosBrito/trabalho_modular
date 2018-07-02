package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;
import br.edu.granbery.trabalhomodular.util.JPAUtil;

public class NotaFiscalController {
	
	private GenericCrud<NotaFiscal, Integer> gci = CrudFactory.buildCrudFor(NotaFiscal.class);
	private static final String SELECT_COUNT_QUERY = "SELECT COUNT(c.id) FROM %s as c";
	private EntityManager entityManager = JPAUtil.getEntityManager();
	
	public NotaFiscal create(NotaFiscal notaFiscal) {
		return gci.create(notaFiscal);
	}

	public NotaFiscal update(NotaFiscal notaFiscal) {
		return gci.update(notaFiscal);
	}
	
	public NotaFiscal find(Integer id) {
		return gci.find(id);
	}
	
	public List<NotaFiscal> findAll() {
		return gci.findAll();
	}
	
	public void delete(NotaFiscal notafiscal) {
		gci.delete(notafiscal.getId());
	}
	
	public Object countAll() {
		entityManager.getTransaction().begin();
		Object count = entityManager.createQuery(String.format(SELECT_COUNT_QUERY, NotaFiscal.class.getSimpleName())).getSingleResult();
		entityManager.getTransaction().commit();
		return count;
	}
	
	public Double getTotalAverage() {
		Long count = (Long) countAll();
		Double total = getTotalValue(findAll());
		return total/count;
	}

	private Double getTotalValue(List<NotaFiscal> notas) {
		Double total = 0d;
		for(NotaFiscal nota : notas) {
			total =+ nota.getValor();
		}
		return total;
	}
	
	public Double getAverageNota(Integer id) {
		NotaFiscal nf = find(id);
		Integer count = nf.getQtdItens();
		Double total = nf.getValor();
		return total/count;
	}
	
	public Double getMaxNota() {
		return getBiggerValue(findAll());
	}

	private Double getBiggerValue(List<NotaFiscal> notas) {
		Double value = 0d;
		for(NotaFiscal nota : notas) {
			value = nota.getValor() > value ? nota.getValor() : value; 
		}
		return value;
	}
	
	public Integer getValueBiggerThanTenThousand() {
		return countValueBiggerThan(10_000d);
	}
	
	private Integer countValueBiggerThan(Double value) {
		Integer count = 0;
		List<NotaFiscal> notas = findAll();
		for(NotaFiscal nota : notas) {
			if(nota.getValor() > value) {
				count++;
			}
		}
		return count;
	}
	
	public Integer getQtdBiggerThanTen() {
		return countQtdBiggerThan(10);
	}
	
	private Integer countQtdBiggerThan(Integer value) {
		Integer count = 0;
		List<NotaFiscal> notas = findAll();
		for(NotaFiscal nota: notas) {
			if(nota.getQtdItens() > value) {
				count++;
			}
		}
		return count;
	}
	
}
