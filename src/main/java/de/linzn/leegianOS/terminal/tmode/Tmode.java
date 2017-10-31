package de.linzn.leegianOS.terminal.tmode;

import de.linzn.leegianOS.App;

import java.util.TreeMap;

public class Tmode {
    private App app;

    private TreeMap<String, Itmode> terminalExecutes;

    public Tmode(App app) {
        this.app = app;
        this.terminalExecutes = new TreeMap<String, Itmode>();
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
