package com.visfull.web.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

	/**
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String base = "http://127.0.0.1:8090/visfull-site\\posters\\1354962697718.jpg";
		System.out.println(base.replaceAll("\\/","\\\\"));
	}

	public static void printType(Type type) {
		if (type instanceof Class) {
			Class t = (Class) type;
			System.out.print(t.getName());
		} else if (type instanceof TypeVariable) {
			TypeVariable t = (TypeVariable) type;
			System.out.print(t.getName());
			printTypes(t.getBounds(), " extends ", " & ", "");
		} else if (type instanceof WildcardType) {
			WildcardType t = (WildcardType) type;
			System.out.print("?");
			printTypes(t.getLowerBounds(), " extends ", " & ", "");
			printTypes(t.getUpperBounds(), " super ", " & ", "");
		} else if (type instanceof ParameterizedType) {
			ParameterizedType t = (ParameterizedType) type;
			Type owner = t.getOwnerType();
			if (owner != null) {
				printType(owner);
				System.out.print(".");
			}
			printType(t.getRawType());
			printTypes(t.getActualTypeArguments(), "<", ", ", ">");
		} else if (type instanceof GenericArrayType) {
			GenericArrayType t = (GenericArrayType) type;
			System.out.print("");
			printType(t.getGenericComponentType());
			System.out.print("[]");
		}

	}

	public static void printTypes(Type[] types, String pre, String sep,
			String suf) {
		if (types.length > 0)
			System.out.print(pre);
		for (int i = 0; i < types.length; i++) {
			if (i > 0)
				System.out.print(sep);
			printType(types[i]);
		}
		if (types.length > 0)
			System.out.print(suf);
	}
}
