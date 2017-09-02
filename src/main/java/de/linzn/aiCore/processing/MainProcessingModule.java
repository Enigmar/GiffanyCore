package de.linzn.aiCore.processing;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.processing.readInMain.InputProcessing;

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
        this.app.heartbeat.runTaskAsynchronous(task);
    }

}
