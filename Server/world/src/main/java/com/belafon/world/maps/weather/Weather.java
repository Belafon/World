package com.belafon.world.maps.weather;

import com.belafon.world.maps.place.Place;
import com.belafon.world.visibles.creatures.Creature;

public class Weather {
    public enum TypeWeather {
        nothing, rain, heavy_rain, storm, thunderstorm
    }

    public volatile int durationOfRain = 0;
    private volatile int clouds = 0;
    private int weather = 0;
    public volatile int visibility = 0; // can be changed by fog for example

    /**
     * Represents weather in concrete position.
     * this object is moving with the wind in the sky.
     * @param durationOfRain
     * @param clouds
     * @param weather
     */
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
        synchronized (place.creatures) {
            for (Creature creature : place.creatures)
                creature.writer.surrounding.setWeather(this);
        }
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds, Place place) {
        this.clouds = clouds;
        synchronized(place.creatures){
            for (Creature creature : place.creatures)
                creature.writer.surrounding.setClouds(place);
        }
    }

    public int getVisibility() {
        return visibility;
    }
}
