package de.linzn.aiCore.internal.skillsApi;


import de.linzn.aiCore.App;

public class SkillManageApi {
    public App app;
    public WeatherAPI weatherApi;
    public PowerApi powerControl;
    public NetworkDevicesApi networkDevicesApi;
    public StatusAPI statusApi;

    public SkillManageApi(App app) {
        this.app = app;
        this.weatherApi = new WeatherAPI(app);
        this.powerControl = new PowerApi(app);
        this.networkDevicesApi = new NetworkDevicesApi(app);
        this.statusApi = new StatusAPI(app);
    }
}
