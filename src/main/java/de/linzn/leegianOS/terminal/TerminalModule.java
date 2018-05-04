/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.leegianOS.terminal;

import de.linzn.leegianOS.LeegianOSApp;
import de.linzn.leegianOS.internal.objectDatabase.clients.SkillClient;
import de.linzn.leegianOS.internal.objectDatabase.clients.TerminalSkillClient;
import de.linzn.leegianOS.internal.processor.MainProcessor;
import de.linzn.leegianOS.terminal.tmode.Tmode;

import java.util.Arrays;
import java.util.UUID;

public class TerminalModule implements Runnable {
    public UUID clientUUID;
    private LeegianOSApp leegianOSApp;
    private boolean terminalMode;
    private Tmode termCommands;

    public TerminalModule(LeegianOSApp leegianOSApp) {
        LeegianOSApp.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
        this.leegianOSApp = leegianOSApp;
        this.clientUUID = UUID.randomUUID();
        this.leegianOSApp.heartbeat.runTaskAsynchronous(this);
        this.termCommands = new Tmode(this.leegianOSApp);
        TerminalSkillClient terminalSkillClient = new TerminalSkillClient();
        this.leegianOSApp.skillClientList.put(terminalSkillClient.clientUUID, terminalSkillClient);
    }

    @Override
    public void run() {
        while (this.leegianOSApp.isAlive.get()) {
            String[] inputArray;
            String input;
            if (this.terminalMode) {
                input = System.console().readLine("T/M$: ");
                inputArray = input.split(" ");
            } else {
                input = System.console().readLine();
                inputArray = input.split(" ");
            }
            String keyword = inputArray[0];

            if (keyword.equalsIgnoreCase("tmode")) {
                if (this.terminalMode) {
                    this.terminalMode = false;
                    LeegianOSApp.logger("Tmode disabled");
                } else {
                    this.terminalMode = true;
                    LeegianOSApp.logger("Tmode enabled");
                }
            } else {

                if (this.terminalMode) {
                    // Direct command
                    String[] valueArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    if (!this.termCommands.runCommand(keyword, valueArray)) {
                        LeegianOSApp.logger("No tmode input.");
                    }
                } else {
                    Runnable runnable = () -> {
                        SkillClient skillClient = this.leegianOSApp.skillClientList.get(new UUID(0, 0));
                        MainProcessor mainProcessor = new MainProcessor(skillClient, input);
                        mainProcessor.processing();
                    };
                    this.leegianOSApp.heartbeat.runTaskAsynchronous(runnable);
                }
            }
        }
    }

}
