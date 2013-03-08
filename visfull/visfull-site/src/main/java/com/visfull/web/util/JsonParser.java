package com.visfull.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JsonParser<E, K> {

	public E json2Bean(Class<E> clazzE, Class<K> clazzK, String json)
			throws InstantiationException, IllegalAccessException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {
		E e = null;
		if (json != null && !"".equals(json)) {
			e = clazzE.newInstance();
			// JSONTokener jsonTokener = new JSONTokener(json);
			if (clazzE.isAssignableFrom(ArrayList.class)) {
				K objK = clazzK.newInstance();
				Field[] fields = clazzK.getDeclaredFields();
				for (Field field : fields) {
					System.out.println(field.getName());
					if (field.getType().isAssignableFrom(Long.class)){
						field.setAccessible(true);
						field.set(objK, "123");
					}
				}
				Method method = clazzE.getMethod("add", Object.class);
				method.invoke(e, objK);
			} else {

			}
		}
		return e;
	}

}
