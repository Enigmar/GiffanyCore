package de.linzn.aiCore;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.inputProcessing.InputProcessing;
import de.linzn.aiCore.inputProcessing.network.NetworkProcessing;
import de.linzn.aiCore.inputProcessing.terminal.TerminalProcessing;
import de.linzn.aiCore.settings.AiSettings;

public class App {
	//The main instance
	public static App appInstance;
	//Define new variables for later
	public AiSettings aiSettings;
	public InputProcessing inputProc;
	public NetworkProcessing networkProc;
	public TerminalProcessing terminalProc;
	public MySQLDatabase mysqlData;
	public Heartbeat heartbeat;
	//The alive value for the heartbeat thread
	public boolean isAlive;
	//The task list for next heartbeat
	public LinkedList<Runnable> taskList = new LinkedList<Runnable>();

	//Main for init this framework
	public static void main(String[] args) {
		App.logger("Creating new App instance.");
		new App(args);
	}

	//The default logger
	public static void logger(String log) {
		System.out.println("[AiCore]: " + log);
	}

	//The instance
	public App(String[] args) {
		appInstance = this;
		loadModules();
	}

	//Load the modules for the framework
	private void loadModules() {
		this.heartbeat = new Heartbeat(appInstance);
		new Thread(this.heartbeat).start();
		this.aiSettings = new AiSettings(appInstance);
		this.inputProc = new InputProcessing(appInstance);
		this.networkProc = new NetworkProcessing(appInstance);
		this.terminalProc = new TerminalProcessing(appInstance);
		this.mysqlData = new MySQLDatabase(appInstance);
	}

	//Add a sync task to the heartbeat tasklist
	public void runTaskSync(Runnable sync) {
		this.taskList.add(sync);
	}

	//add a async task to the heartbeat tasklist
	public void runTaskAsync(Runnable async) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
						.submit(async);
			}

		};

		this.taskList.add(runnable);
	}

}
