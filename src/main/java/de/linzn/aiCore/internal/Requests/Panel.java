package de.linzn.aiCore.internal.Requests;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.PanelContainer;
import de.linzn.aiCore.processing.network.writeBack.PanelData;

public class Panel {
    public ClientContainer clientContainer;

    public Panel(ClientContainer clientContainer) {
        this.clientContainer = clientContainer;
    }

    public void sendUpdates() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                new PanelData().sendDataToPanel(clientContainer, getData());
            }
        };
        App.appInstance.runTaskAsync(task);
    }

    private PanelContainer getData() {
        PanelContainer panelContainer = new PanelContainer();
        panelContainer.setDate("02.01.2000");
        panelContainer.setTemperature(16.4f);
        panelContainer.setTime("12:44");
        return panelContainer;
    }
}
