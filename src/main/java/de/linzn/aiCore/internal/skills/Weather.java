package de.linzn.aiCore.internal.skills;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.IObjectClass;
import de.linzn.aiCore.internal.Reflector;
import de.linzn.aiCore.internal.skillsApi.PowerControl;

public class Weather implements IObjectClass {

    @Override
    public void runTask(String function) {
        new Reflector().functionRunner(this, function);
    }


    public void today() {
        System.out.println("Asking for weather today");

    }

    public void oneDay() {
        System.out.println("Asking for weather next day");
    }

    public void twoDay() {
        System.out.println("Asking for weather two day");
    }

}
