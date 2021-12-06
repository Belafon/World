package Game.Maps.MapGenerators;

import Game.Maps.Place.Place;
import Game.Maps.Map;
import Game.Maps.Weather.Weather;

public interface MapGenerator {
    public Place[][] generateMap(int sizeX, int sizeY, Map map);
    public Weather[][] generateClouds(int sizeX, int sizeY);
}
