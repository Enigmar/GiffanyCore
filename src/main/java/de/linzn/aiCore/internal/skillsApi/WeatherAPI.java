package de.linzn.aiCore.internal.skillsApi;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;


public class WeatherAPI {
    private String apiKey = "0b33ae42ba7acd32ab5ca39535b7568f";
    private String location = "Niederwuerzbach";
    private OpenWeatherMap owm;
    public CurrentWeather cwd;
    public DailyForecast dfd;


    public WeatherAPI(int day){
        owm = new OpenWeatherMap(this.apiKey);
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setLang(OpenWeatherMap.Language.GERMAN);
        try {
            if (day <= 0) {
                cwd = owm.currentWeatherByCityName(this.location);
            } else {
                dfd = owm.dailyForecastByCityName(this.location, (byte) day);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
