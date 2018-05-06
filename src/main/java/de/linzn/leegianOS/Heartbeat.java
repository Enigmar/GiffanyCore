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


import de.linzn.leegianOS.internal.objectDatabase.TimeData;
import de.linzn.leegianOS.internal.objectDatabase.TimedTimeData;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Heartbeat implements Runnable, Executor {
    private Heartbeat heartbeat;
    private LeegianOSApp leegianOSApp;
    /* The list with the pending tasks*/
    private LinkedList<Runnable> taskList;
    private String prefix = this.getClass().getSimpleName() + "->";

    public Heartbeat(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(prefix + "creating Instance ");
        this.heartbeat = this;
        this.taskList = new LinkedList<>();
        this.leegianOSApp = leegianOSApp;
        this.leegianOSApp.isAlive.set(true);
    }

    /* The task worker */
    public void run() {
        /* Heartbeat is running until isAlive is false */
        while (this.leegianOSApp.isAlive.get()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!this.taskList.isEmpty()) {
                Runnable run = this.taskList.removeFirst();
                heartbeat.execute(run);
            }

        }
        /* Shutdown the heartbeat Thread */
        this.leegianOSApp.networkProc.deleteNetwork();
        System.exit(0);

    }

    /* Run the execution from the heartbeat Thread */
    @Override
    public void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Run Task directly in the heartbeat Thread */
    public void runTaskSynchronous(Runnable sync) {
        this.taskList.add(sync);
    }

    /* Run repeat Task in a new Thread */
    public void runRepeatTaskAsynchronous(Runnable run, TimeData timeData) {
        runRepeatTaskAsynchronous(run, timeData.delay, timeData.period, timeData.timeUnit);
    }

    /* Run repeat Task in a new Thread */
    public void runRepeatTaskAsynchronous(Runnable run, int delay, int period, TimeUnit timeUnit) {
        Runnable runnable = () -> Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(run, delay, period, timeUnit);
        this.taskList.add(runnable);
        LeegianOSApp.logger(prefix + "runRepeatTaskAsynchronous->" + "Add new RepeatTaskAsynchronous Thread");
    }

    /* Run timed Task in a new Thread */
    public void runTimedTaskAsynchronous(Runnable run, TimedTimeData timedTimeData) {
        runTimedTaskAsynchronous(run, timedTimeData.days, timedTimeData.hours, timedTimeData.minutes);
    }

    public void runTimedTaskAsynchronous(Runnable run, int days, int hours, int minutes) {
        long times = getTimerTime(days, hours, minutes);
        Runnable runnable = () -> Executors.newSingleThreadScheduledExecutor().schedule(run, times, TimeUnit.MILLISECONDS);
        this.taskList.add(runnable);
        LeegianOSApp.logger(prefix + "runTimedTaskAsynchronous->" + "Add new TimedTaskAsynchronous Thread->" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(times + System.currentTimeMillis())));
    }

    /* Run daily timed Task in a new Thread */
    public void runTimedDailyRepeatAsynchronous(Runnable run, TimedTimeData timedTimeData) {
        runTimedDailyRepeatAsynchronous(run, timedTimeData.days, timedTimeData.hours, timedTimeData.minutes);
    }

    public void runTimedDailyRepeatAsynchronous(Runnable run, int days, int hours, int minutes) {
        long times = getTimerTime(days, hours, minutes);
        Runnable runnable = () -> Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(run, times, 1000 * 60 * 60 * 24, TimeUnit.MILLISECONDS);
        this.taskList.add(runnable);
        LeegianOSApp.logger(prefix + "runTimedDailyRepeatAsynchronous->" + "Add new DailyTimedTaskAsynchronous Thread->DailyAt " + new SimpleDateFormat("HH:mm").format(new Date(times + System.currentTimeMillis())));
    }

    /* Run repeat Task in a new Thread */
    public void runDelayedTaskAsynchronous(Runnable run, int delay, TimeUnit timeUnit) {
        Runnable runnable = () -> Executors.newSingleThreadScheduledExecutor().schedule(run, delay, timeUnit);
        this.taskList.add(runnable);
        LeegianOSApp.logger(prefix + "runDelayedTaskAsynchronous->" + "Add new DelayedTaskAsynchronous Thread");
    }


    /* Run single Task in a new Thread */
    public void runTaskAsynchronous(Runnable async) {
        Runnable runnable = () -> Executors.newSingleThreadExecutor().submit(async);
        this.taskList.add(runnable);
        LeegianOSApp.logger(prefix + "runTaskAsynchronous->" + "Add new TaskAsynchronous Thread");
    }


    private long getTimerTime(int days, int hours, int minute) {
        long selectTime = selectTime(days, hours, minute);
        if (selectTime < System.currentTimeMillis()) {
            selectTime = selectTime(days + 1, hours, minute);
        }
        return selectTime - System.currentTimeMillis();
    }

    private long selectTime(int days, int hours, int minute) {
        Calendar selectedDay = new GregorianCalendar();
        TimeZone timezone = TimeZone.getTimeZone("Europe/Berlin");
        selectedDay.setTimeZone(timezone);
        selectedDay.add(Calendar.DATE, days);
        Calendar result = new GregorianCalendar(selectedDay.get(Calendar.YEAR),
                selectedDay.get(Calendar.MONTH), selectedDay.get(Calendar.DATE), hours,
                minute);
        return result.getTime().getTime();
    }

}
