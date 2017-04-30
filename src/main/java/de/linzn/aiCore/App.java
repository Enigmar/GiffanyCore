package de.linzn.aiCore;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.inputProcessing.InputProcessing;
import de.linzn.aiCore.inputProcessing.network.NetworkProcessing;
import de.linzn.aiCore.inputProcessing.terminal.TerminalProcessing;

public class App {
	
	public static App appInstance;
	
	public InputProcessing inputProc;
	public NetworkProcessing networkProc;
	public TerminalProcessing terminalProc;
	public MySQLDatabase mysqlData;

	public static void main(String[] args) {
		appInstance = new App(args);

	}
	
	
	public App(String[] args){
		loadModules();
	}
	
	private void loadModules(){
		this.inputProc = new InputProcessing(appInstance);
		this.networkProc = new NetworkProcessing(appInstance);
		this.terminalProc = new TerminalProcessing(appInstance);
		this.mysqlData = new MySQLDatabase(appInstance);
	}

}
