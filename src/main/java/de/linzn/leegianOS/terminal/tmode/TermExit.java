package de.linzn.leegianOS.terminal.tmode;

import de.linzn.leegianOS.App;

public class TermExit implements Itmode {

    @Override
    public boolean executeTerminal() {
        System.out.println("Set alive mode to false.");
        App.appInstance.isAlive.set(false);
        return true;
    }

}
