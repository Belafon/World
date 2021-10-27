package Game.Maps;

import Game.Game;
import Game.Maps.MapGenerators.MapGenerator;
import Game.Maps.Place.Place;
import Game.Maps.Weather.Sky;
public class Map {
    public final int sizeX;
    public final int sizeY;
    public final Place[][] places;
    public final Sky sky;
    /*public Map(String pathToFile){

    }*/
    public Game game;
    public Map(int sizeX, int sizeY, MapGenerator mapGenerator, int directionOfWind, int strengthOfWind, Game game){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.game = game;
        places = mapGenerator.generateMap(sizeX, sizeY, this);
        sky = new Sky(mapGenerator.generateClouds(sizeX, sizeY), directionOfWind, strengthOfWind, this);
    }

    public Map(int sizeX, int sizeY, MapGenerator mapGenerator){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        places = mapGenerator.generateMap(sizeX, sizeY, this);
        sky = null;
    }

    public String logMap(){
        String log = "";
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                log += places[i][j].log();
            }
            log += "\n";
        }
        return log;
    }

    public String logTemperature(){
        String log = "";
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++)
                log += places[i][j].getTemperature() + " ";
            log += "\n";
        }
        return log;
    }
}
