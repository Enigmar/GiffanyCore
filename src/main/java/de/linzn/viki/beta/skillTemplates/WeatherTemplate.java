package de.linzn.viki.beta.skillTemplates;

import de.linzn.viki.App;
import de.linzn.viki.beta.ifaces.ISkillTemplate;
import de.linzn.viki.beta.ifaces.ParentSkill;
import de.linzn.viki.beta.ifaces.SubSkill;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;


public class WeatherTemplate implements ISkillTemplate {
    private ParentSkill parentSkill;
    private SubSkill subSkill;

    @Override
    public void setEnv(ParentSkill parentSkill, SubSkill subSkill) {
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public App app;

    public void getWeather() {
        try {
            OpenWeatherMap owm = new OpenWeatherMap((String) App.appInstance.vikiConfiguration.weatherKey);
            owm.setUnits(OpenWeatherMap.Units.METRIC);
            owm.setLang(OpenWeatherMap.Language.GERMAN);
            String location = (String) App.appInstance.vikiConfiguration.location;
            App.logger("Get new weather data for location " + location);
            App.logger("Get new weather for current time");
            CurrentWeather weatherCurrent = owm.currentWeatherByCityName(location);
            System.out.println("Wetter aktuell: " + weatherCurrent.getMainInstance().getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
