package de.linzn.aiCore.processing.terminal;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ProcessType;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.processing.terminal.tmode.Tmode;

import java.util.Arrays;
import java.util.UUID;

public class TerminalModule implements Runnable {
    public UUID clientUUID;
    private App app;
    private boolean terminalMode;
    private Tmode termCommands;

    public TerminalModule(App app) {
        App.logger("Loading TerminalModule");
        this.app = app;
        this.clientUUID = UUID.randomUUID();
        this.app.heartbeat.runTaskAsynchronous(this);
        this.termCommands = new Tmode(this.app);
    }

    @Override
    public void run() {
        while (this.app.isAlive.get()) {
            String[] inputArray;
            String input;
            if (this.terminalMode) {
                input = System.console().readLine("T/M$: ");
                inputArray = input.split(" ");
            } else {
                input = System.console().readLine(": ");
                inputArray = input.split(" ");
            }
            String keyword = inputArray[0];

            if (keyword.equalsIgnoreCase("tmode")) {
                if (this.terminalMode) {
                    this.terminalMode = false;
                    App.logger("Tmode disabled");
                } else {
                    this.terminalMode = true;
                    App.logger("Tmode enabled");
                }
            } else {

                if (this.terminalMode) {
                    // Direct command
                    String[] valueArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                    if (!this.termCommands.runCommand(keyword, valueArray)) {
                        App.logger("No tmode input.");
                    }
                } else {
                    ClientContainer clientContainer = new ClientContainer(clientUUID, ProcessType.TERMINAL);
                    this.app.inputProc.provideInput(clientContainer, input);
                }
            }
        }
    }

}
