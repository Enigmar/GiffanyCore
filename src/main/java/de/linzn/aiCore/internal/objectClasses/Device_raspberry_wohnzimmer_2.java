package de.linzn.aiCore.internal.objectClasses;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.apiClasses.PowerControl;

public class Device_raspberry_wohnzimmer_2 implements IObjectClass {

	@Override
	public void runTask(String function) {
		new Reflector().functionRunner(this, function);
	}

	public void restart() {
		App.logger("Restart Raspel-2");
		new PowerControl().restartUniversal("10.40.0.11", 22, "root");
	}


}
