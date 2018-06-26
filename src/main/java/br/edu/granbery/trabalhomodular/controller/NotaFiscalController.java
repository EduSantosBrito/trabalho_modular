package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;

public class NotaFiscalController {
	
	private GenericCrud<NotaFiscal, Integer> gci = CrudFactory.buildCrudFor(NotaFiscal.class);
	
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
}
