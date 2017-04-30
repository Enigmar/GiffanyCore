package de.linzn.aiCore;

import java.util.concurrent.Executor;

public class Heartbeat implements Runnable, Executor {
	private Heartbeat heartbeat;
	private App app;

	public Heartbeat(App app) {
		App.logger("Creating new Heartbeat instance.");
		this.heartbeat = this;
		this.app = app;
		this.app.isAlive = true;
	}

	public void run() {

		while (this.app.isAlive) {
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
		this.app.networkProc.deleteNetwork();
		System.exit(0);

	}

	@Override
	public void execute(Runnable runnable) {
		runnable.run();
	}

}
