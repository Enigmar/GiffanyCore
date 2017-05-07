package de.linzn.aiCore.internal.objectClasses;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.apiClasses.PowerControl;

public class Device_desktop_niklas implements IObjectClass {

	@Override
	public void runTask(String function) {
		new Reflector().runTask(this, function);
	}

	public void restart() {
		App.logger("Restart niklaspc");
		new PowerControl().restartUniversal("10.40.0.10", 22, "root");
	}

	public void wakeUp() {
		App.logger("Wakeup NiklasPC");
		new PowerControl().wakeOnLan("4c:cc:6a:0e:16:58");
	}

}
