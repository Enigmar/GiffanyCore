package de.linzn.aiCore.inputProcessing.terminal.execudes;

import de.linzn.aiCore.inputProcessing.terminal.TerminalExecutes;
import de.linzn.aiCore.internal.objectClasses.Test_debug;

public class TermTest implements TerminalExecutes {

	@Override
	public boolean executeTerminal() {
		new Test_debug().runTask("test123");
		return true;
	}

}
