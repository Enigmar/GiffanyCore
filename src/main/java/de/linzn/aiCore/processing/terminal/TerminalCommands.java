package de.linzn.aiCore.processing.terminal;

import de.linzn.aiCore.App;
import de.linzn.aiCore.processing.terminal.execudes.TermExit;
import de.linzn.aiCore.processing.terminal.execudes.TermTest;

import java.util.TreeMap;

public class TerminalCommands {
    private App app;

    private TreeMap<String, TerminalExecutes> terminalExecutes;

    public TerminalCommands(App app) {
        this.app = app;
        this.terminalExecutes = new TreeMap<String, TerminalExecutes>();
        this.initialTerminalExecudes();
    }

    public boolean runCommand(String command, String[] args) {
        if (command.length() != 0) {
            if (this.terminalExecutes.containsKey(command)) {
                return this.terminalExecutes.get(command).executeTerminal();
            }
        }
        return false;
    }

    private void initialTerminalExecudes() {
        this.terminalExecutes.put("exit", new TermExit());
        this.terminalExecutes.put("test", new TermTest());
    }

}
