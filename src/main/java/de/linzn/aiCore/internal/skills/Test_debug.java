package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;

import java.lang.reflect.InvocationTargetException;

public class Test_debug implements IObjectClass {

    @Override
    public void runTask(String function) {
        new Reflector().functionRunner(this, function);

    }

    public void test123() {
        App.logger("This is test123");
    }

}
