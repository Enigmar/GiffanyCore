package de.linzn.aiCore.inputProcessing.terminal.execudes;

import de.linzn.aiCore.inputProcessing.terminal.TerminalExecutes;
import de.linzn.aiCore.internal.objectClasses.TestObject;

public class TermTest implements TerminalExecutes {

	@Override
	public boolean executeTerminal() {
		new TestObject().runTask("test123");
		return true;
	}

}
