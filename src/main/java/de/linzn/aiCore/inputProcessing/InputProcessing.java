package de.linzn.aiCore.inputProcessing;

import de.linzn.aiCore.App;

public class InputProcessing {
	private App app;

	public InputProcessing(App app) {
		App.logger("Loading InputPocessing module.");
		this.app = app;
	}

	public void receiveInput(String args[]) {
		//Processing content later here...
		for (String arg : args) {
			System.out.print(arg + " ");
		}
		System.out.println("");
	}

}
