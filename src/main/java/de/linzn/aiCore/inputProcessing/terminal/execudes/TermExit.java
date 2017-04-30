package de.linzn.aiCore.inputProcessing.terminal.execudes;

import de.linzn.aiCore.App;
import de.linzn.aiCore.inputProcessing.terminal.TerminalExecutes;

public class TermExit implements TerminalExecutes {

	@Override
	public boolean executeTerminal() {
		System.out.println("Set alive mode to false.");
		App.appInstance.isAlive = false;
		return true;
	}

}
