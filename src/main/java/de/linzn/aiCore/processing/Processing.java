package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;

public class Processing {
	private App app;

	public Processing(App app) {
		App.logger("Loading Pocessing module.");
		this.app = app;
	}

	public void receiveInput(String input) {
		new InputProcessing(app).processingInput(input);
	}

	public void receiveInsertText(String textSentence, String textResult) {
		new InsertProcessing(app).processingInsertText(textSentence, textResult);
	}

	public void receiveInsertObject(String objectName, String objectClass, String keywordName, String keywordFunction,
			String objectResult) {
		new InsertProcessing(app).processingInsertObject(objectName, objectClass, keywordName, keywordFunction,
				objectResult);
	}

}
