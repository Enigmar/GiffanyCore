package de.linzn.leegianOS.terminal.tmode;

import de.linzn.leegianOS.LeegianOSApp;

import java.util.TreeMap;

public class Tmode {
    private LeegianOSApp leegianOSApp;

    private TreeMap<String, Itmode> terminalExecutes;

    public Tmode(LeegianOSApp leegianOSApp) {
        this.leegianOSApp = leegianOSApp;
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
