package de.linzn.viki.skillTemplates;

import de.linzn.viki.App;
import de.linzn.viki.internal.ifaces.ISkillTemplate;
import de.linzn.viki.internal.ifaces.ParentSkill;
import de.linzn.viki.internal.ifaces.RequestOwner;
import de.linzn.viki.internal.ifaces.SubSkill;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;


public class WeatherTemplate implements ISkillTemplate {
    public App app;
    private RequestOwner requestOwner;
    private ParentSkill parentSkill;
    private SubSkill subSkill;
    private String prefix = this.getClass().getSimpleName() + "->";

    @Override
    public void setEnv(RequestOwner requestOwner, ParentSkill parentSkill, SubSkill subSkill) {
        this.requestOwner = requestOwner;
        this.subSkill = subSkill;
        this.parentSkill = parentSkill;
    }

    public void getWeatherCurrent() {
        String location;
        String key;
        if (this.parentSkill != null) {
            location = (String) this.parentSkill.serial_data.get("location");
            key = (String) this.parentSkill.serial_data.get("weatherKey");
        } else {
            location = (String) this.subSkill.serial_data.get("location");
            key = (String) this.subSkill.serial_data.get("weatherKey");
        }
        try {
            OpenWeatherMap owm = new OpenWeatherMap(key);
            owm.setUnits(OpenWeatherMap.Units.METRIC);
            owm.setLang(OpenWeatherMap.Language.GERMAN);
            App.logger(prefix + "getWeather-->" + "location " + location);
            CurrentWeather weatherCurrent = owm.currentWeatherByCityName(location);
            System.out.println("Wetter aktuell: " + weatherCurrent.getMainInstance().getTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
