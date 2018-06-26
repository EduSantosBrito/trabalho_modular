package br.edu.granbery.trabalhomodular.controller;

import java.util.List;

import br.edu.granbery.trabalhomodular.model.Item;
import br.edu.granbery.trabalhomodular.util.CrudFactory;
import br.edu.granbery.trabalhomodular.util.GenericCrud;

public class ItemController {
	
	private GenericCrud<Item, Integer> gci = CrudFactory.buildCrudFor(Item.class);
	
	public Item create(Item Item) {
		return gci.create(Item);
	}

	public Item update(Item Item) {
		return gci.update(Item);
	}
	
	public Item find(Integer id) {
		return gci.find(id);
	}
	
	public List<Item> findAll() {
		return gci.findAll();
	}
	
	public void delete(Item item) {
		gci.delete(item.getId());
	}
}
