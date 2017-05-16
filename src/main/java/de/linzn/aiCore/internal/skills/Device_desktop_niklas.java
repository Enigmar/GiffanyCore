package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;

public class Device_desktop_niklas implements IObjectClass {

    @Override
    public void runTask(String function) {
        new Reflector().functionRunner(this, function);
    }


    public void wakeup() {
        App.appInstance.skillApi.powerControl.wakeOnLan("4c:cc:6a:0e:16:58");
    }

}
