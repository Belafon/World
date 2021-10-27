package Game.Maps.Weather;

import Game.Creatures.Creature;
import Game.Maps.Place.Place;

public class Weather {
    public enum TypeWeather{
        nothing, rain, heavy_rain, storm, thunderstorm
    }
    public volatile int durationOfRain = 0; 
    private volatile int clouds;
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
        for(Creature creature : place.creatures)creature.writer.surrounding.setWeather(this);
    }
    public int getClouds() {
        return clouds;
    }
    public void setClouds(int clouds, Place place) {
        this.clouds = clouds;
        for(Creature creature : place.creatures)creature.writer.surrounding.setClouds(creature.getPosition());

    }
    public int getVisibility() {
        return visibility;
    }
}
