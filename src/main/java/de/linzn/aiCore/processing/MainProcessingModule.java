package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.processing.readInMain.InputProcessing;
import de.linzn.aiCore.processing.readInMain.InsertProcessing;

public class MainProcessingModule {
    private App app;

    public MainProcessingModule(App app) {
        App.logger("Loading MainProcessingModule");
        this.app = app;
    }

    public void provideInput(ClientContainer clientContainer, String input) {

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
        this.app.runTaskAsync(task);
    }
}
