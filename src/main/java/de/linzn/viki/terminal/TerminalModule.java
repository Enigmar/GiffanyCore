package de.linzn.viki.terminal;

import de.linzn.viki.App;
import de.linzn.viki.beta.processor.SkillProcessor;
import de.linzn.viki.terminal.tmode.Tmode;

import java.util.Arrays;
import java.util.UUID;

public class TerminalModule implements Runnable {
    public UUID clientUUID;
    private App app;
    private boolean terminalMode;
    private Tmode termCommands;

    public TerminalModule(App app) {
        App.logger(this.getClass().getSimpleName() + "->" + "creating Instance ");
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
                    SkillProcessor skillProcessor = new SkillProcessor(input);
                    skillProcessor.processing();
                }
            }
        }
    }

}
