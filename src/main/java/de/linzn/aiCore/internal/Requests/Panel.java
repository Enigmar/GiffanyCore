package de.linzn.aiCore.internal.Requests;


import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.container.ClientContainer;
import de.linzn.aiCore.internal.container.PanelContainer;
import de.linzn.aiCore.processing.network.writeBack.PanelData;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;
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

    public void printTest() {
        PanelContainer test = getData();
        System.out.println("Date: " + test.temperature);
    }

    private PanelContainer getData() {
        PanelContainer panelContainer = new PanelContainer();
        CurrentWeather cwd = getWeather();
        panelContainer.setDate(new Date().getTime());
        panelContainer.setTemperature(cwd.getMainInstance().getTemperature());
        panelContainer.setRefreshDate(new Date().getTime());
        return panelContainer;
    }

    private CurrentWeather getWeather() {
        OpenWeatherMap owm = new OpenWeatherMap("0b33ae42ba7acd32ab5ca39535b7568f");
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        CurrentWeather cwd = null;
        try {
            cwd = owm.currentWeatherByCityName("Niederwuerzbach");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cwd;

    }
}
