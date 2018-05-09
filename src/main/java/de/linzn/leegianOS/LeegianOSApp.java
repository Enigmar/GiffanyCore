/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS;

import de.linzn.leegianOS.configuration.AppConfiguration;
import de.linzn.leegianOS.database.DatabaseModule;
import de.linzn.leegianOS.internal.objectDatabase.clients.SkillClient;
import de.linzn.leegianOS.internal.scheduler.SchedulerProcessor;
import de.linzn.leegianOS.internal.scheduler.SkillProcessor;
import de.linzn.leegianOS.network.NetworkModule;
import de.linzn.leegianOS.terminal.TerminalModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LeegianOSApp {
    // The main instance
    public static LeegianOSApp leegianOSAppInstance;
    public static Logger fileLogger;
    // Define new variables for later
    public AppConfiguration appConfiguration;
    public NetworkModule networkProc;
    public TerminalModule terminalProc;
    public DatabaseModule mysqlData;
    public Heartbeat heartbeat;
    public SkillProcessor skillProcessor;
    public SchedulerProcessor schedulerProcessor;
    public HashMap<UUID, SkillClient> skillClientList;
    // The alive value for the heartbeat thread
    public AtomicBoolean isAlive;
    private long start_time;

    // The instance
    public LeegianOSApp(String[] args) {
        this.start_time = System.nanoTime();
        leegianOSAppInstance = this;
        this.isAlive = new AtomicBoolean();
        this.skillClientList = new HashMap<>();
        this.heartbeat = new Heartbeat(leegianOSAppInstance);
        Thread heart = new Thread(this.heartbeat);
        heart.setName("heartbeat");
        heart.start();
        loadModules();
        finishStartup();

    }

    // Main for init this framework
    public static void main(String[] args) {
        logSetup();
        LeegianOSApp.logger(LeegianOSApp.class.getSimpleName() + "->" + "creating Instance ", true);
        new LeegianOSApp(args);


    }

    // The default fileLogger
    public static synchronized void logger(String log, boolean writeToFile) {
        System.out.print("[" + Thread.currentThread().getName() + "] " + log + "\n");
        System.out.flush();
        if (writeToFile) {
            fileLogger.info("[" + Thread.currentThread().getName() + "] " + log);
        }
    }

    private static void logSetup() {
        fileLogger = Logger.getLogger("leegianOS");
        fileLogger.setUseParentHandlers(false);
        FileHandler fh;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        try {
            File logsDir = new File("logs");
            if (!logsDir.exists()) {
                logsDir.mkdir();
            }
            // This block configure the fileLogger with handler and formatter
            fh = new FileHandler("logs/" + dateFormat.format(new Date().getTime()) + ".log");
            fileLogger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    // Load the modules for the framework
    private void loadModules() {

        Runnable settings = () -> appConfiguration = new AppConfiguration(leegianOSAppInstance);
        Runnable mysql = () -> mysqlData = new DatabaseModule(leegianOSAppInstance);
        Runnable network = () -> networkProc = new NetworkModule(leegianOSAppInstance);
        Runnable terminal = () -> terminalProc = new TerminalModule(leegianOSAppInstance);
        Runnable schedulers = () -> schedulerProcessor = new SchedulerProcessor(leegianOSAppInstance);
        Runnable test = () -> skillProcessor = new SkillProcessor(leegianOSAppInstance);

        this.heartbeat.runTaskSynchronous(settings);
        this.heartbeat.runTaskSynchronous(mysql);
        this.heartbeat.runTaskSynchronous(network);
        this.heartbeat.runTaskSynchronous(terminal);
        this.heartbeat.runTaskSynchronous(test);
        this.heartbeat.runTaskSynchronous(schedulers);

    }

    private void finishStartup() {
        Runnable finish = () -> logger("LeegianOS startup finished in " + (int) ((System.nanoTime() - start_time) / 1e6) + " ms.", true);
        this.heartbeat.runTaskSynchronous(finish);
    }


    public String getVersion() {
        String version;
        String res = "META-INF/maven/de.linzn/leegianOS/pom.properties";
        URL url = Thread.currentThread().getContextClassLoader().getResource(res);
        if (url == null) {
            version = "SS";
        } else {
            Properties props = new Properties();
            try {
                props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(res));
            } catch (IOException e) {
                e.printStackTrace();
            }
            version = props.getProperty("version");
        }

        return version;
    }


}
