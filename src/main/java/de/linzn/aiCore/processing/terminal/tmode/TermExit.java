package de.linzn.aiCore.processing.terminal.tmode;

import de.linzn.aiCore.App;

public class TermExit implements Itmode {

    @Override
    public boolean executeTerminal() {
        System.out.println("Set alive mode to false.");
        App.appInstance.isAlive.set(false);
        return true;
    }

}
