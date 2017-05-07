package de.linzn.aiCore.processing.terminal;

import java.util.Arrays;

import de.linzn.aiCore.App;

public class TerminalProcessing implements Runnable {
	private App app;
	private boolean terminalMode;
	private TerminalCommands termCommands;

	public TerminalProcessing(App app) {
		App.logger("Loading TerminalProcessing module.");
		this.app = app;
		this.app.runTaskAsync(this);
		this.termCommands = new TerminalCommands(this.app);
	}

	@Override
	public void run() {
		while (this.app.isAlive) {
			String[] inputArray;
			String input;
			if (this.terminalMode) {
				input = System.console().readLine("#~: ");
				inputArray = input.split(" ");
			} else {
				input = System.console().readLine(": ");
				inputArray = input.split(" ");
			}
			String keyword = inputArray[0];

			if (keyword.equalsIgnoreCase("terminal")) {
				if (this.terminalMode) {
					this.terminalMode = false;
					App.logger("Terminal mode off!");
				} else {
					this.terminalMode = true;
					App.logger("Terminal mode on!");
				}
			} else {

				if (this.terminalMode) {
					// Direct command
					String[] valueArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
					if (!this.termCommands.runCommand(keyword, valueArray)) {
						App.logger("No result for this input!");
					}
				} else {
					this.app.inputProc.receiveInput(input);
				}
			}
		}
	}

}
