package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.granbery.trabalhomodular.dto.PessoaDTO;
import br.edu.granbery.trabalhomodular.model.Item;
import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.model.Pessoa;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;
import br.edu.granbery.trabalhomodular.util.JPAUtil;

public class NotaFiscalController {
	
	private ItemController ic = new ItemController();
	private GenericCrud<NotaFiscal, Integer> gci = CrudFactory.buildCrudFor(NotaFiscal.class);
	private static final String SELECT_COUNT_QUERY = "SELECT COUNT(c.id) FROM %s as c";
	private static final String SELECT_MAX_ESTADO_EMITENTE_QUERY = "SELECT MAX(p.estado) FROM %s as c INNER JOIN %s as p ON p.id = c.emitente";
	private static final String SELECT_MAX_ESTADO_DESTINATARIO_QUERY = "SELECT MAX(p.estado) FROM %s as c INNER JOIN %s as p ON p.id = c.destinatario";
	private static final String SELECT_MAX_DOCUMENTO_EMITENTE_QUERY = "SELECT new br.edu.granbery.trabalhomodular.dto.PessoaDTO(p.nome, MAX(p.documento)) FROM %s as c INNER JOIN %s as p ON p.id = c.emitente";
	private static final String SELECT_MAX_DOCUMENTO_DESTINATARIO_QUERY = "SELECT new br.edu.granbery.trabalhomodular.dto.PessoaDTO(p.nome, MAX(p.documento)) FROM %s as c INNER JOIN %s as p ON p.id = c.destinatario";
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
		gci.delete(notafiscal);
	}
	
	public Object countAll() {
		entityManager.getTransaction().begin();
		Object count = entityManager.createQuery(String.format(SELECT_COUNT_QUERY, NotaFiscal.class.getSimpleName()))
									.getSingleResult();
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
	
	public Double getAverageItem() {
		List<Item> itens = ic.findAll();
		Integer count = itens.size();
		Double total = 0d;
		for(Item item:itens) {
			total+=item.getPreco();
		}
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
		return countValueBiggerThan(10000d);
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
	
	public Object getMaxEstadoEmitente() {
		entityManager.getTransaction().begin();
		Object estado = entityManager.createQuery(String.format(SELECT_MAX_ESTADO_EMITENTE_QUERY, NotaFiscal.class.getSimpleName(), Pessoa.class.getSimpleName()))
					 .getSingleResult();
		entityManager.getTransaction().commit();
		return estado;
	}
	public Object getMaxEstadoDestinatario() {
		entityManager.getTransaction().begin();
		Object estado = entityManager.createQuery(String.format(SELECT_MAX_ESTADO_DESTINATARIO_QUERY, NotaFiscal.class.getSimpleName(), Pessoa.class.getSimpleName()))
					 .getSingleResult();
		entityManager.getTransaction().commit();
		return estado;		
	}
	public PessoaDTO getMaxDocumentoEmitente() {
		entityManager.getTransaction().begin();
		PessoaDTO pessoaDTO = (PessoaDTO) entityManager.createQuery(String.format(SELECT_MAX_DOCUMENTO_EMITENTE_QUERY, NotaFiscal.class.getSimpleName(), Pessoa.class.getSimpleName())).getSingleResult();
		entityManager.getTransaction().commit();
		return pessoaDTO;
	}
	
	public PessoaDTO getMaxDocumentoDestinatario() {
		entityManager.getTransaction().begin();
		PessoaDTO pessoaDTO = (PessoaDTO) entityManager.createQuery(String.format(SELECT_MAX_DOCUMENTO_DESTINATARIO_QUERY, NotaFiscal.class.getSimpleName(), Pessoa.class.getSimpleName())).getSingleResult();
		entityManager.getTransaction().commit();
		return pessoaDTO;
	}
	
}
