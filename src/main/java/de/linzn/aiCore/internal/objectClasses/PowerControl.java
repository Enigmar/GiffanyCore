package de.linzn.aiCore.internal.objectClasses;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;

public class PowerControl implements IObjectClass {

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
		App.logger("Restart raspel3");
		restartUniversal("10.40.0.10", 22, "root");
	}
	
	public void wakeNiklasPC(){
		App.logger("Wakeup NiklasPC");
		wakeOnLan("4c:cc:6a:0e:16:58");
	}
	
	
	//
	
	private void wakeOnLan(String mac){
		//Need "apt-get install etherwake" packet installed
		try {
			Runtime.getRuntime().exec("etherwake " + mac).waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void restartUniversal(String ip, int port, String user){
		//Need deposit ssh key first
		try {
			Runtime.getRuntime().exec("ssh " + user + "@" + ip + " -p " + port + " 'reboot'").waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
