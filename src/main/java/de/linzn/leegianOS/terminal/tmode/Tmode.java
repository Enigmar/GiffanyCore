/*
 * Copyright (C) 2017. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.leegianOS.terminal.tmode;

import de.linzn.leegianOS.LeegianOSApp;

import java.util.TreeMap;

public class Tmode {
    private LeegianOSApp leegianOSApp;

    private TreeMap<String, Itmode> terminalExecutes;

    public Tmode(LeegianOSApp leegianOSApp) {
        this.leegianOSApp = leegianOSApp;
        this.terminalExecutes = new TreeMap<>();
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
        this.terminalExecutes.put("sreload", new TermReloadSchedulers());
    }

}
