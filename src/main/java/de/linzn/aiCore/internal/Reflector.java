package de.linzn.aiCore.internal;

import java.lang.reflect.InvocationTargetException;

public class Reflector {

	public void functionRunner(IObjectClass classObject, String function) {
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

	@SuppressWarnings("unchecked")
	public void classRunner(ObjectContainer objectCon, KeywordContainer keywordCon) {
		Class<IObjectClass> act;
		try {
			act = (Class<IObjectClass>) Class.forName("de.linzn.aiCore.internal.objectClasses."
					+ Character.toUpperCase(objectCon.classname.charAt(0)) + objectCon.classname.substring(1));
			IObjectClass objectclass = act.newInstance();
			objectclass.runTask(keywordCon.function);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
