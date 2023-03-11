package com.belafon.Game.Maps.Place;


import com.belafon.Console.ConsolePrint;
import com.belafon.Game.Maps.Map;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Weather.Weather;

public class Place extends UnboundedPlace {
    public final Map map;
    public final int positionX;
    public final int positionY;
    public String music;
    public String picture;
    public int altitude;
    public volatile int visibility = 0;

    private static Map printNewPlaceOnlyOfOneMap;

    public Place(Map map, int positionX, int positionY, int altitude, TypeOfPlace typeOfPlace) {
        super(typeOfPlace, map.game);
        if(printNewPlaceOnlyOfOneMap == null)printNewPlaceOnlyOfOneMap = map;
        this.map = map;
        this.positionX = positionX;
        this.positionY = positionY;
        this.altitude = altitude;
        music = typeOfPlace.getMusic();
        picture = typeOfPlace.getPicture();
    }

    public int getTemperature() {
        int temperature = 30 - (altitude / 80) + map.game.time.partOfDay.temperatureChange(); // altitude = 1000, ->  30 - (1000 / 80) = 30 - 12 = 8 degrees celsius
        if(map.sky != null){
            Weather weather = map.sky.getWeather(this);
            temperature -= weather.getClouds();
            temperature -= weather.getWeather();
        }
        return temperature;
    }

    public int getVisibility() {
        return visibility + map.sky.getWeather(this).getVisibility();
    }

    public String log(){
        String log = "Resources = [ ";
        for(Resource resource : resources.values()) log += resource.typeOfResource.name + " ";
        if(map == printNewPlaceOnlyOfOneMap) ConsolePrint.gameInfo("Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = " + altitude  + "  " + typeOfPlace.name +  "  " + log + "]");
        return log;
    }
}