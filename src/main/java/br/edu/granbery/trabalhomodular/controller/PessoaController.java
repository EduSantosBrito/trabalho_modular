package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import br.edu.granbery.trabalhomodular.model.Pessoa;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;

public class PessoaController {
	
	private GenericCrud<Pessoa, Integer> gci = CrudFactory.buildCrudFor(Pessoa.class);
	
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
}
