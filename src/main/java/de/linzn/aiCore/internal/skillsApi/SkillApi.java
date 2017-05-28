package de.linzn.aiCore.internal.skillsApi;


import de.linzn.aiCore.App;

public class SkillApi {
    public App app;
    public WeatherAPI weatherApi;
    public PowerApi powerControl;
    public ModemApi modemApi;
    public StatusAPI statusApi;

    public SkillApi(App app) {
        this.app = app;
        this.weatherApi = new WeatherAPI(app);
        this.powerControl = new PowerApi(app);
        this.modemApi = new ModemApi(app);
        this.statusApi = new StatusAPI(app);
    }
}
