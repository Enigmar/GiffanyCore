package de.linzn.aiCore.internal.requests;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.PanelContainer;
import de.linzn.aiCore.processing.network.writeBack.PanelData;

import java.util.Date;

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
        panelContainer.setDate(new Date().getTime());
        panelContainer.setTemperature(App.appInstance.skillApi.weatherApi.weatherCurrent.getMainInstance().getTemperature());
        panelContainer.setRefreshDate(new Date().getTime());
        return panelContainer;
    }


}
