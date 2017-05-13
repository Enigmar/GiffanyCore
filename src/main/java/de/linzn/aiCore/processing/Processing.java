package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;

public class Processing {
    private App app;

    public Processing(App app) {
        App.logger("Loading Pocessing module.");
        this.app = app;
    }

    public void receiveInput(ClientContainer clientContainer, String input) {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                new InputProcessing(app).processingInput(clientContainer, input);
            }
        };
        this.app.runTaskAsync(task);
    }

    public void receiveInsertText(String textSentence, String textResult) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                new InsertProcessing(app).processingInsertText(textSentence, textResult);
            }
        };
        this.app.runTaskAsync(task);
    }

    public void receiveInsertObject(String objectName, String objectClass, String keywordName, String keywordFunction,
                                    String objectResult) {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                new InsertProcessing(app).processingInsertObject(objectName, objectClass, keywordName, keywordFunction,
                        objectResult);
            }
        };
    }
}
