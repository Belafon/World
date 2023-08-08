package com.example.world.game.maps.weather;

import java.util.HashMap;

import android.util.Log;
import android.view.View;
import com.example.world.game.maps.weather.ColorViewTransition.Color;
import com.example.world.logs.Logs;


public class Weather {
    private static final String TAG = "Weather";
    private int clouds = 0;
    private int weather = 0;
    private NamePartOfDay partOfDay = NamePartOfDay.unknown;
    private WeatherPanel panel;

    public void setWeatherView(View view) {
        this.panel = new WeatherPanel(view);
    }

    public enum NamePartOfDay {
        after_midnight, // starts at 3.0 hours today -> 60 of ticks today
        sunrise_1, // 5.5 -> 110
        sunrise_2, // 6.25 -> 125
        morning, // 7.0 -> 140
        afternoon, // 15.0 -> 300
        sunset_1, // 18.5 -> 370
        sunset_2, // 19.25 -> 385
        night, // 20.0 -> 400
        unknown
    }

    private final HashMap<NamePartOfDay, PartOfDay> partsOfDay = new HashMap<>(){
        {
            put(NamePartOfDay.unknown, new PartOfDay(NamePartOfDay.unknown, new Color(0, 0, 100, 120), 400)); // dark blue
            put(NamePartOfDay.after_midnight, new PartOfDay(NamePartOfDay.after_midnight, new Color(0, 0, 10, 140), 400)); // dark blue
            put(NamePartOfDay.sunrise_1, new PartOfDay(NamePartOfDay.sunrise_1, new Color(255, 80, 0, 70), 200));//173, 216, 30, 10), 50)); // light blue
            put(NamePartOfDay.sunrise_2, new PartOfDay(NamePartOfDay.sunrise_2, new Color(80, 40, 60, 20), 100)); // pink
            put(NamePartOfDay.morning, new PartOfDay(NamePartOfDay.morning, new Color(135, 206, 235, 5), 250)); // sky blue
            put(NamePartOfDay.afternoon, new PartOfDay(NamePartOfDay.afternoon, new Color(255, 255, 255, 0), 340)); // white
            put(NamePartOfDay.sunset_1, new PartOfDay(NamePartOfDay.sunset_1, new Color(255, 80, 10, 70), 200)); // sky blue
            put(NamePartOfDay.sunset_2, new PartOfDay(NamePartOfDay.sunset_2, new Color(80, 5, 80, 80), 60)); // pink
            put(NamePartOfDay.night, new PartOfDay(NamePartOfDay.night, new Color(5, 5, 50, 160), 400)); // light blue
        }
    };

    

    private String[] cloudsNames = new String[]{
        "clear sky",
        "clear sky with few small clouds",
        "sky with small clouds",
        "slightly translucent",
        "partly cloudy",
        "cloudy",
        "very cloudy",
        "dark cloudy"
    };

    private String[] weatherNames = new String[]{
        "idle",
        "gentle rain",
        "rain",
        "heavy rain",
        "storm"
    };

    public enum WeatherType{
        idle(new Color(0, 0, 0, 0), 0),
        gentle_rain(new Color(-90, -90, -70, 40), 0),
        rain(new Color(-90, -90, -70, 75), 0),
        heavy_rain(new Color(-110, -110, -100, 100), 40),
        storm(new Color(-200, -200, -180, 100), 10);
        public final Color color;
        public final int frequencyOfLightnings;
        WeatherType(Color color, int frequencyOfLightnings) {
            this.color = color;
            this.frequencyOfLightnings = frequencyOfLightnings;
        }

    }

    /**
     * This changes current clouds in the current 
     * context.
     * @param args is the message from the server.
     */
    public void setClouds(String[] args) {
        clouds = Integer.parseInt(args[2]);
        String cloudsTextDescription = cloudsNames[clouds];
        if(panel != null)
            panel.setClouds(cloudsTextDescription);
    }
    
    /**
     * This changes weather in the current context.
     * All panels are updated according the new 
     * weather type.
     * @param args is the message from the server.
     */
    public void setWeather(String[] args) {
        weather = Integer.parseInt(args[2]);
        String weatherTextDescription = weatherNames[weather];
        WeatherType type = WeatherType.values()[weather];
        if(panel != null)
            panel.setWeather(weatherTextDescription, type);
    }

    /**
     * This changes weather in the current context.
     * All panels are updated according the new 
     * weather type.
     * @param args is the message from the server.
     */
    public void setPartOfDay(String[] args) {
        partOfDay = NamePartOfDay.valueOf(args[2]);

        if(Logs.WEATHER_FILTER)
            Log.d(TAG, "setPartOfDay: " + partOfDay.name());

        if(panel != null)
            panel.setPartOfDay(partsOfDay.get(partOfDay));
    }
}
