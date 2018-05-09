/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.internal.scheduler;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.interfaces.ISkill;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Objects;

public class SkillProcessor {
    public HashMap<String, Class<ISkill>> skillList;
    // Define variables
    private LeegianOSApp leegianOSApp;
    private String prefix = this.getClass().getSimpleName() + "->";

    public SkillProcessor(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(prefix + "creating Instance ", true);
        this.leegianOSApp = leegianOSApp;
        this.skillList = new HashMap<>();
        this.loadSkills();
    }

    public void loadSkill(Class<ISkill> iSkillClass, boolean globalLoad) {
        if (!globalLoad) {
            this.skillList.remove(iSkillClass.getSimpleName());
        }
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "loading " + iSkillClass.getSimpleName(), true);

        skillList.put(iSkillClass.getSimpleName(), iSkillClass);
    }

    public void loadSkills() {
        skillList.clear();
        for (File classFiles : Objects.requireNonNull(new File("skills").listFiles())) {
            try {
                String class_name = classFiles.getName().replace(".class", "");
                ClassLoader cl = new URLClassLoader(new URL[]{new File("").toURI().toURL()});
                Class<ISkill> iSkillClass = (Class<ISkill>) cl.loadClass("skills." + Character.toUpperCase(class_name.charAt(0)) + class_name.substring(1));
                this.loadSkill(iSkillClass, true);
            } catch (ClassNotFoundException | MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

}
