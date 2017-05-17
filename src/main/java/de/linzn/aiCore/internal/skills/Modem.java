package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;

public class Modem implements IObjectClass {

    @Override
    public void runTask(String function) {
        new Reflector().functionRunner(this, function);
    }


    public void restart() {
        App.appInstance.skillApi.modemApi.restartCBNModem();
    }

}
