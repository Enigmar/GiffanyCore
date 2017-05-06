package de.linzn.aiCore.internal.objectClasses;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;

public class Plex implements IObjectClass {

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

	public void restartRaspel3() {
		App.logger("Restart raspel");
		restartUniversal("10.40.0.10", 22, "root");
	}
	
	private void restartUniversal(String ip, int port, String user){
		try {
			Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
