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
import de.linzn.leegianOS.internal.interfaces.IScheduler;
import de.linzn.leegianOS.internal.objectDatabase.clients.SchedulerSkillClient;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class SchedulerProcessor {
    public HashMap<UUID, IScheduler> schedulersList;
    // Define variables
    private LeegianOSApp leegianOSApp;
    private String prefix = this.getClass().getSimpleName() + "->";

    public SchedulerProcessor(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(prefix + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.schedulersList = new HashMap<>();
        this.loadSchedulers();
    }

    public void loadScheduler(UUID uuid, Class<IScheduler> iSchedulerClass, boolean globalLoad) {
        if (!globalLoad && this.schedulersList.containsKey(uuid)) {
            this.schedulersList.get(uuid).set_alive(false);
            this.leegianOSApp.skillClientList.remove(uuid);
            this.schedulersList.remove(uuid);
        }
        try {
            IScheduler schedulerInstance = iSchedulerClass.newInstance();
            schedulerInstance.set_alive(true);
            this.schedulersList.put(schedulerInstance.schedulerUUID(), schedulerInstance);
            this.leegianOSApp.skillClientList.put(schedulerInstance.schedulerUUID(), new SchedulerSkillClient(schedulerInstance.schedulerUUID()));
            LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "loading " + iSchedulerClass.getSimpleName());

            leegianOSApp.heartbeat.runRepeatTaskAsynchronous(() -> {
                check_valid_scheduler(schedulerInstance);
                try {
                    schedulerInstance.scheduler();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, schedulerInstance.scheduler_timer());

            leegianOSApp.heartbeat.runRepeatTaskAsynchronous(() -> {
                check_valid_scheduler(schedulerInstance);
                try {
                    schedulerInstance.loopback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, schedulerInstance.loopBack_timer());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    public void loadSchedulers() {
        for (IScheduler iScheduler : schedulersList.values()) {
            iScheduler.set_alive(false);
            leegianOSApp.skillClientList.remove(iScheduler.schedulerUUID());
        }
        schedulersList.clear();
        for (File classFiles : Objects.requireNonNull(new File("schedulers").listFiles())) {
            if (classFiles.isFile()) {
                String class_name = classFiles.getName().replace(".class", "");
                try {
                    ClassLoader cl = new URLClassLoader(new URL[]{new File("").toURI().toURL()});
                    Class<IScheduler> act = (Class<IScheduler>) cl.loadClass("schedulers." + Character.toUpperCase(class_name.charAt(0)) + class_name.substring(1));
                    IScheduler schedulerInstance = act.newInstance();
                    UUID uuid = schedulerInstance.schedulerUUID();
                    this.loadScheduler(uuid, act, true);
                } catch (ClassNotFoundException | InstantiationException | SecurityException | IllegalAccessException | IllegalArgumentException | MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void check_valid_scheduler(IScheduler iScheduler) {
        if (!iScheduler.is_alive()) {
            LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "terminate old scheduler ");
            Thread.currentThread().interrupt();
            Thread.currentThread().stop();
        }
    }
}
