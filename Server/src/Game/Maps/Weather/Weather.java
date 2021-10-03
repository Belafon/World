package Game.Maps.Weather;

import Game.Creatures.Player;
import Game.Maps.Place.Place;

public class Weather {
    public enum TypeWeather{
        nothing, rain, heavy_rain, storm, thunderstorm
    }
    public volatile int durationOfRain = 0; 
    private volatile int clouds;
    private int weather = 0;
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
        for(Player player : place.players)player.client.writer.setWeather(this);
    }
    public int getClouds() {
        return clouds;
    }
    public void setClouds(int clouds, Place place) {
        this.clouds = clouds;
        for(Player player : place.players)player.client.writer.setClouds(player.getPosition());

    }
}
