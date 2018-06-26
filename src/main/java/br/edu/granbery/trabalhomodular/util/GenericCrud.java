package br.edu.granbery.trabalhomodular.util;

import java.io.Serializable;
import java.util.List;

public interface GenericCrud<T, PK extends Serializable> {
	T create(T t);
	T find(PK id);
	T update(T t);
	void delete(PK id);
	List<T> findAll();
}
