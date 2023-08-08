package com.example.world.game.maps.weather;

import java.util.HashMap;

import android.util.Log;
import android.view.View;
import com.example.world.game.maps.weather.ColorViewTransition.Color;
import com.example.world.logs.Logs;
import java.util.ArrayList;
import com.example.world.game.maps.weather.Cloud;

public class Weather {
    private static final String TAG = "Weather";
    private int cloudsIndex = 0;
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
            put(NamePartOfDay.unknown, new PartOfDay(NamePartOfDay.unknown, new Color(0, 0, 50, 120), 400)); // dark blue
            put(NamePartOfDay.after_midnight, new PartOfDay(NamePartOfDay.after_midnight, new Color(0, 0, 30, 180), 400)); // dark blue
            put(NamePartOfDay.sunrise_1, new PartOfDay(NamePartOfDay.sunrise_1, new Color(255, 10, 0, 50), 200));//173, 216, 30, 10), 50)); // light blue
            put(NamePartOfDay.sunrise_2, new PartOfDay(NamePartOfDay.sunrise_2, new Color(80, 40, 60, 20), 100)); // pink
            put(NamePartOfDay.morning, new PartOfDay(NamePartOfDay.morning, new Color(135, 206, 235, 5), 250)); // sky blue
            put(NamePartOfDay.afternoon, new PartOfDay(NamePartOfDay.afternoon, new Color(255, 255, 255, 0), 340)); // white
            put(NamePartOfDay.sunset_1, new PartOfDay(NamePartOfDay.sunset_1, new Color(255, 10, 0, 50), 200)); // sky blue
            put(NamePartOfDay.sunset_2, new PartOfDay(NamePartOfDay.sunset_2, new Color(15, 5, 80, 150), 60)); // pink
            put(NamePartOfDay.night, new PartOfDay(NamePartOfDay.night, new Color(0, 0, 10, 190), 400)); // light blue
        }
    };

    public final Cloud[] cloudTypes = new Cloud[]{
        new Cloud(new Color(0, 0, 0, 0), 0, 0, 0, false, "clear sky"),
        new Cloud(new Color(255, 255, 255, 30), 60, 40, 80, true, "clear sky with few small clouds"),
        new Cloud(new Color(255, 255, 255, 50), 40, 40, 120, true, "sky with small clouds"),
        new Cloud(new Color(255, 255, 255, 70), 40, 40, 120, true, "slightly translucent"),

        new Cloud(new Color(255, 255, 255, 100), 40, 20, 1000, true, "partly cloudy"),
        new Cloud(new Color(300, 255, 255, 120), 0, 20, 1000, true, "cloudy"),
        new Cloud(new Color(320, 255, 255, 140), 0, 20, 1000, true, "very cloudy"),
        new Cloud(new Color(355, 355, 355, 160), 0, 20, 1000, true, "dark cloudy")

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
        idle(new Color(255, 255, 255, 0), 0),
        rain(new Color(-90, -90, -70, 110), 0),
        heavy_rain(new Color(-90, -90, -70, 130), 0),
        storm(new Color(-110, -110, -100, 150), 60),
        thunderstorm(new Color(-200, -200, -180, 170), 30);
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
        cloudsIndex = Integer.parseInt(args[2]);
        Cloud cloud = cloudTypes[cloudsIndex];
        if(panel != null)
            panel.setClouds(cloud);
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
