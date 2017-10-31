package de.linzn.leegianOS.terminal.tmode;

import de.linzn.leegianOS.LeegianOSApp;

public class TermExit implements Itmode {

    @Override
    public boolean executeTerminal() {
        System.out.println("Set alive mode to false.");
        LeegianOSApp.leegianOSAppInstance.isAlive.set(false);
        return true;
    }

}
