package com.cebbys.celib.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class CelibArrays {

	public static <T> ArrayList<T> getArrayList(T[] array) {
		return (ArrayList<T>) Arrays.asList(array);
	}
	
	public static <T extends Enum<?>> ArrayList<T> getArrayList(Class<T> e) {
		return getArrayList(e.getEnumConstants());
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(ArrayList<T> list) {
		return (T[]) list.toArray();
	}
}
