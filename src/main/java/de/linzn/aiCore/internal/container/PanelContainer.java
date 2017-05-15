package de.linzn.aiCore.internal.container;


public class PanelContainer {
    public float temperature;
    public long date;
    public long refreshDate;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setRefreshDate(long refreshDate) {
        this.refreshDate = refreshDate;
    }
}
