package de.linzn.aiCore.processing.terminal.execudes;

import de.linzn.aiCore.internal.skills.Test_debug;
import de.linzn.aiCore.processing.terminal.TerminalExecutes;

public class TermTest implements TerminalExecutes {

    @Override
    public boolean executeTerminal() {
        new Test_debug().runTask("test123");
        return true;
    }

}
