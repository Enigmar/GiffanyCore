package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;

public class Device_raspberry_niklas_3 implements IObjectClass {

    @Override
    public void runTask(String function) {
        new Reflector().functionRunner(this, function);
    }

    public void restart() {
        App.logger("Restart Raspel-3");
        App.appInstance.skillApi.powerControl.restartUniversal("10.40.0.10", 22, "root");
    }


}
