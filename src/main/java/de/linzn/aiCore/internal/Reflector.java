package de.linzn.aiCore.internal;

import java.lang.reflect.InvocationTargetException;

public class Reflector {
	
	public void runTask(IObjectClass classObject, String function){
		java.lang.reflect.Method method;
		try {
			method = classObject.getClass().getMethod(function);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return;
		}

		try {
			method.invoke(classObject);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
