package com.venkat.gerericDao;

import java.util.List;
import java.util.Map;

public class StrUtils {

	public static boolean isEmpty(String isEmpty) {
		return isEmpty.isEmpty();
	}

	public static boolean isEmpty(List<Object> isEmpty) {
		return isEmpty!=null;
	}

	public static boolean isEmpty(Page page) {
		return page == null;
	}

	public static String changeName(String entityName) {
		return new String(entityName);
	}

	public static boolean isEmpty(Map<String, String> isEmpty) {
		return isEmpty!=null;
	}

	public static boolean isEmpty(Object isEmpty) {
		return isEmpty == null;
	}

	public static String firstCodeToUpperCase(String entityName) {
		String cap = entityName;
		return cap.substring(0, 1).toUpperCase() + cap.substring(1);
	}
}
