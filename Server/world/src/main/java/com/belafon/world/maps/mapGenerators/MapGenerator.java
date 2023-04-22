package com.belafon.world.maps.mapGenerators;

import com.belafon.world.maps.Map;
import com.belafon.world.maps.place.Place;
import com.belafon.world.maps.weather.Weather;

public interface MapGenerator {
    public Place[][] generateMap(int sizeX, int sizeY, Map map);
    public Weather[][] generateClouds(int sizeX, int sizeY);
}