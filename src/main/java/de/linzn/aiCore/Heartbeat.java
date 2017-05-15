package de.linzn.aiCore;

import java.util.concurrent.Executor;

public class Heartbeat implements Runnable, Executor {
    // Define instances and variables
    private Heartbeat heartbeat;
    private App app;

    // The class
    public Heartbeat(App app) {
        App.logger("Creating new Heartbeat instance.");
        this.heartbeat = this;
        this.app = app;
        this.app.isAlive.set(true);
    }

    // The runnable for extends runnable
    public void run() {
        // Is running, until alive is false
        while (this.app.isAlive.get()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!this.app.taskList.isEmpty()) {
                Runnable run = this.app.taskList.removeFirst();
                heartbeat.execute(run);
            }

        }
        // Shutdown the Heartbeat
        this.app.networkProc.deleteNetwork();
        System.exit(0);

    }

    // The Executor for running something in the main thread
    @Override
    public void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
