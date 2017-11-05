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

public class TermExit implements Itmode {

    @Override
    public boolean executeTerminal() {
        System.out.println("Set alive mode to false.");
        LeegianOSApp.leegianOSAppInstance.isAlive.set(false);
        return true;
    }

}
