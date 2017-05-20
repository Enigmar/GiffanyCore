package de.linzn.aiCore.internal.skillsApi;

import de.linzn.aiCore.App;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;


public class WeatherAPI {
    public App app;
    public CurrentWeather weatherCurrent;
    public DailyForecast weatherNextDay;
    public DailyForecast weatherNextTwoDay;
    private OpenWeatherMap owm;


    public WeatherAPI(App app) {
        this.app = app;
        owm = new OpenWeatherMap((String) this.app.mysqlData.dbsetting.getSetting("weather_apiKey"));
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setLang(OpenWeatherMap.Language.GERMAN);
        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                parseWeather();
            }
        };
        App.appInstance.runRepeatTaskAsync(runTask, 2000, 1000 * 120);


    }

    private void parseWeather() {
        try {
            String location = (String) this.app.mysqlData.dbsetting.getSetting("weather_location");
            App.logger("Get new weather data for location " + location);
            App.logger("Get new weather for current time");
            weatherCurrent = owm.currentWeatherByCityName(location);
            App.logger("Get new weather for next day");
            weatherNextDay = owm.dailyForecastByCityName(location, (byte) 1);
            App.logger("Get new weather for next 2 day");
            weatherNextTwoDay = owm.dailyForecastByCityName(location, (byte) 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
