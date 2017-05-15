package de.linzn.aiCore.processing.terminal.execudes;

import de.linzn.aiCore.internal.Requests.Panel;
import de.linzn.aiCore.processing.terminal.TerminalExecutes;

public class TermTest implements TerminalExecutes {

    @Override
    public boolean executeTerminal() {
        new Panel(null).printTest();
        return true;
    }

}
