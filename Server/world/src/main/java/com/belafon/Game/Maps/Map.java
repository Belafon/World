package com.belafon.Game.Maps;

import com.belafon.Game.World;
import com.belafon.Game.Maps.MapGenerators.MapGenerator;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Weather.Sky;
public class Map {
    public final int sizeX;
    public final int sizeY;
    public final Place[][] places;
    public final Sky sky;
    /*public Map(String pathToFile){

    }*/
    public World game;
    public Map(int sizeX, int sizeY, MapGenerator mapGenerator, int directionOfWind, int strengthOfWind, World game){
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
