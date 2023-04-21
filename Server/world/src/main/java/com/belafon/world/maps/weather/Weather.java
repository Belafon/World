package com.belafon.world.maps.weather;

import com.belafon.world.creatures.Creature;
import com.belafon.world.maps.place.Place;

public class Weather {
    public enum TypeWeather {
        nothing, rain, heavy_rain, storm, thunderstorm
    }

    public volatile int durationOfRain = 0;
    private volatile int clouds = 0;
    private int weather = 0;
    public volatile int visibility = 0; // can be changed by fog for example

    public Weather(int durationOfRain, int clouds, int weather) {
        this.durationOfRain = durationOfRain;
        this.clouds = clouds;
        this.weather = weather;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather, Place place) {
        this.weather = weather;
        place.getCreatures((creatures) -> {
            for (Creature creature : creatures)
                creature.writer.surrounding.setWeather(this);
            return null;
        });
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds, Place place) {
        this.clouds = clouds;
        place.getCreatures((creatures) -> {
            for (Creature creature : creatures)
                creature.writer.surrounding.setClouds(place);
            return null;
        });
        

    }

    public int getVisibility() {
        return visibility;
    }
}
