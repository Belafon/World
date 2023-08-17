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

    private final HashMap<NamePartOfDay, PartOfDay> partsOfDay = new HashMap<>() {
        {
            put(NamePartOfDay.unknown, new PartOfDay(NamePartOfDay.unknown, new Color(0, 0, 50, 120), 4800));
            put(NamePartOfDay.after_midnight,
                    new PartOfDay(NamePartOfDay.after_midnight, new Color(0, 0, 30, 180), 4800));
            put(NamePartOfDay.sunrise_1, new PartOfDay(NamePartOfDay.sunrise_1, new Color(320, 10, 0, 40), 2400));
            put(NamePartOfDay.sunrise_2, new PartOfDay(NamePartOfDay.sunrise_2, new Color(80, 40, 60, 20), 2160));
            put(NamePartOfDay.morning, new PartOfDay(NamePartOfDay.morning, new Color(255, 255, 0, 10), 3000));
            put(NamePartOfDay.afternoon, new PartOfDay(NamePartOfDay.afternoon, new Color(255, 200, 0, 10), 4080));
            put(NamePartOfDay.sunset_1, new PartOfDay(NamePartOfDay.sunset_1, new Color(320, 10, 0, 40), 2400));
            put(NamePartOfDay.sunset_2, new PartOfDay(NamePartOfDay.sunset_2, new Color(15, 5, 80, 150), 2160));
            put(NamePartOfDay.night, new PartOfDay(NamePartOfDay.night, new Color(0, 0, 10, 155), 4800));
        }
    };

    public final Cloud[] cloudTypes = new Cloud[] {
            new Cloud(new Color(0, 0, 0, 0), 0, 0, 0, false, true, "clear sky"),
            new Cloud(new Color(-1200, -600, -600, 60), 380, 150, 30, true, true, "clear sky with few small clouds"),
            new Cloud(new Color(-1200, -600, -600, 80), 240, 140, 60, true, true, "sky with small clouds"),
            new Cloud(new Color(-1200, -600, -600, 80), 160, 140, 100, true, true, "slightly translucent"),

            new Cloud(new Color(-1200, -600, -600, 80), 120, 100, 200, true, true, "partly cloudy"),
            new Cloud(new Color(-1200, -600, -600, 90), 120, 100, 200, true, true, "cloudy"),
            new Cloud(new Color(-1200, -600, -600, 90), 0, 100, 1000, true, false, "very cloudy"),
            new Cloud(new Color(-1200, -600, -600, 90), 0, 100, 1000, true, false, "dark cloudy")
    };

    private String[] cloudsNames = new String[] {
            "clear sky",
            "clear sky with few small clouds",
            "sky with small clouds",
            "slightly translucent",
            "partly cloudy",
            "cloudy",
            "very cloudy",
            "dark cloudy"
    };

    private String[] weatherNames = new String[] {
            "idle",
            "gentle rain",
            "rain",
            "heavy rain",
            "storm"
    };

    public enum WeatherType {
        idle(new Color(0, 0, 0, 0), 0),
        rain(new Color(-1200, -1200, -1200, 110), 0),
        heavy_rain(new Color(-1200, -1200, -1200, 120), 0),
        storm(new Color(-355, -1200, -1200, 120), 60),
        thunderstorm(new Color(-1200, -1200, -1200, 120), 30);

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
     * 
     * @param args is the message from the server.
     */
    public void setClouds(String[] args) {
        cloudsIndex = Integer.parseInt(args[2]);
        Cloud cloud = cloudTypes[cloudsIndex];
        if (panel != null)
            panel.setClouds(cloud);
    }

    /**
     * This changes weather in the current context.
     * All panels are updated according the new
     * weather type.
     * 
     * @param args is the message from the server.
     */
    public void setWeather(String[] args) {
        weather = Integer.parseInt(args[2]);
        String weatherTextDescription = weatherNames[weather];
        WeatherType type = WeatherType.values()[weather];
        if (panel != null)
            panel.setWeather(weatherTextDescription, type);
    }

    /**
     * This changes weather in the current context.
     * All panels are updated according the new
     * weather type.
     * 
     * @param args is the message from the server.
     */
    public void setPartOfDay(String[] args) {
        partOfDay = NamePartOfDay.valueOf(args[2]);

        if (Logs.WEATHER_FILTER)
            Log.d(TAG, "setPartOfDay: " + partOfDay.name());

        if (panel != null)
            panel.setPartOfDay(partsOfDay.get(partOfDay));
    }

    public WeatherPanel getPanel() {
        return panel;
    }
}
