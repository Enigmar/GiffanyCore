package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;

public class InsertProcessing {
	private App app;

	public InsertProcessing(App app) {
		this.app = app;
	}

	public void processingInsertText(String textSentence, String textResult) {
		System.out.println(textSentence + " - " + textResult);
	}

	public void processingInsertObject(String objectName, String objectClass, String keywordName,
			String keywordFunction, String objectResult) {
		System.out.println(objectName + " - " + objectClass+ " - " + keywordName+ " - " + keywordFunction+ " - " + objectResult);
	}

}
