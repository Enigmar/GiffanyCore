package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ClientContainer;

public class Processing {
	private App app;

	public Processing(App app) {
		App.logger("Loading Pocessing module.");
		this.app = app;
	}

	public void receiveInput(ClientContainer clientContainer, String input) {
		new InputProcessing(app).processingInput(clientContainer, input);
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
