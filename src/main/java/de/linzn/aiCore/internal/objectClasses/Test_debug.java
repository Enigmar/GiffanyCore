package de.linzn.aiCore.internal.objectClasses;

import java.lang.reflect.InvocationTargetException;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;

public class Test_debug implements IObjectClass {

	@Override
	public void runTask(String function) {
		java.lang.reflect.Method method;
		try {
			method = this.getClass().getMethod(function);
		} catch (SecurityException e) {
			e.printStackTrace();
			return;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return;
		}

		try {
			method.invoke(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public void test123() {
		App.logger("This is test123");
	}

}
