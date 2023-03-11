package com.belafon.Game.Maps.MapGenerators;

import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Map;
import com.belafon.Game.Maps.Weather.Weather;

public interface MapGenerator {
    public Place[][] generateMap(int sizeX, int sizeY, Map map);
    public Weather[][] generateClouds(int sizeX, int sizeY);
}
