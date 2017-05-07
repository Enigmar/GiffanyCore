package de.linzn.aiCore.processing.terminal.execudes;

import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.terminal.TerminalExecutes;

public class TermExit implements TerminalExecutes {

	@Override
	public boolean executeTerminal() {
		System.out.println("Set alive mode to false.");
		App.appInstance.isAlive = false;
		return true;
	}

}
