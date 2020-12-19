package com.cebbys.celib.utilities;

import java.util.ArrayList;

public class CelibArrays {

	public static <T> ArrayList<T> getArrayList(T[] array) {
		ArrayList<T> list = new ArrayList<T>();
		for (T i : array) {
			list.add(i);
		}
		return list;
	}
	
	public static <T extends Enum<?>> ArrayList<T> getArrayList(Class<T> e) {
		return getArrayList(e.getEnumConstants());
		
	}

	public static <T> T[] getArray(ArrayList<T> list) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[list.size()];
		return list.toArray(array);
	}
}
