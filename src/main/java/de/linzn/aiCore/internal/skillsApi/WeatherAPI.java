package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;


public class WeatherAPI {
    public App app;
    private String apiKey = "0b33ae42ba7acd32ab5ca39535b7568f";
    private String location = "Niederwuerzbach";
    private OpenWeatherMap owm;
    public CurrentWeather weatherCurrent;
    public DailyForecast weatherNextDay;
    public DailyForecast weatherNextTwoDay;


    public WeatherAPI(App app) {
        this.app = app;
        owm = new OpenWeatherMap(this.apiKey);
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setLang(OpenWeatherMap.Language.GERMAN);
        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                do {
                    praseWeather();
                    try {
                        Thread.sleep(1000 * 60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);

            }
        };
        App.appInstance.runTaskAsync(runTask);

    }

    private void praseWeather() {
        try {
            App.logger("Get new weather data for location " + this.location);
            App.logger("Get new weather for current time");
            weatherCurrent = owm.currentWeatherByCityName(this.location);
            App.logger("Get new weather for next day");
            weatherNextDay = owm.dailyForecastByCityName(this.location, (byte) 1);
            App.logger("Get new weather for next 2 day");
            weatherNextTwoDay = owm.dailyForecastByCityName(this.location, (byte) 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
