/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */
package de.linzn.leegianOS.internal.scheduler;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.ifaces.IScheduler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class SchedulerProcessor {
    // Define variables
    private LeegianOSApp leegianOSApp;
    private HashMap<String, IScheduler> loadedSchedulers;

    public SchedulerProcessor(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.loadedSchedulers = new HashMap<>();
        this.loadSchedulers();
    }

    public void loadSchedulers() {
        for (IScheduler iScheduler : loadedSchedulers.values()) {
            iScheduler.terminateScheduler();
        }
        loadedSchedulers.clear();
        for (File classFiles : new File("schedulers").listFiles()) {
            if (classFiles.isFile()) {
                String class_name = classFiles.getName().replace(".class", "");
                try {
                    ClassLoader cl = new URLClassLoader(new URL[]{new File("").toURI().toURL()});
                    Class<IScheduler> act = (Class<IScheduler>) cl.loadClass("schedulers." + Character.toUpperCase(class_name.charAt(0)) + class_name.substring(1));
                    LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "loading " + act.getSimpleName());
                    IScheduler schedulerInstance = act.newInstance();
                    this.loadedSchedulers.put(classFiles.getName().toLowerCase(), schedulerInstance);
                    leegianOSApp.heartbeat.runTaskAsynchronous(schedulerInstance::loadScheduler);
                } catch (ClassNotFoundException | InstantiationException | SecurityException | IllegalAccessException | IllegalArgumentException | MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
