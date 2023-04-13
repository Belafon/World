package com.example.maps.weather;

import javax.swing.JPanel;

public class Weather {
    private int clouds = 0;
    private int weather = 0;
    private NamePartOfDay partOfDay = NamePartOfDay.unknown;
    private final WeatherPanel panel;

    public Weather() {
        this.panel = new WeatherPanel();
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

    public void setClouds(String[] args) {
        clouds = Integer.parseInt(args[2]);
        String cloudsTextDescription = switch (clouds) {
            case 0 -> "cleare sky";
            case 1 -> "cleare sky with few small clouds";
            case 2 -> "sky with small clouds";
            case 3 -> "slightly translucent";
            case 4 -> "partly cloudy";
            case 5 -> "cloudy";
            case 6 -> "very cloudy";
            case 7 -> "dark cloudy";
            default -> throw new IllegalArgumentException("Unspported size of cloud");
        };
        panel.setClouds(cloudsTextDescription);
    }
    public void setWeather(String[] args) {
        weather = Integer.parseInt(args[2]);
        String weatherTextDescription = switch(weather){
            case 0 -> "idle";
            case 1 -> "gentle rain";
            case 2 -> "rain";
            case 3 -> "heavy rain";
            case 4 -> "storm";
            default -> throw new IllegalArgumentException("unsupported weather");
        };
        panel.setWeather(weatherTextDescription);
    }

    public void setPartOfDay(String[] args) {
        partOfDay = NamePartOfDay.valueOf(args[2]);
        panel.setPartOfDay(partOfDay.name());
    }
    
    public JPanel getPanel() {
        return panel.getPanel();
    }
}
