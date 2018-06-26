package br.edu.granbery.trabalhomodular.util;

public final class CrudFactory {

	private CrudFactory() {}
	
	public static <T> GenericCrud<T, Integer> buildCrudFor(Class<T> type) {
		return new GenericCrudImpl<T, Integer>(type);
	
	}
	
}
