package Game.Maps.Place;

import java.util.ArrayList;

import Console.ConsolePrint;
import Game.Creatures.Creature;
import Game.Creatures.Player;
import Game.Items.Item;
import Game.Maps.Map;
import Game.Maps.PlaceEffects.PlaceEffect;
import Game.Maps.Resources.Resource;
import Game.Maps.Resources.TypeOfResourceWithPressence;
import Game.Maps.Weather.Weather;

public class Place {
    public final Map map;
    public final int positionX;
    public final int positionY;
    public final ArrayList<Creature> creatures = new ArrayList<Creature>();
    public final ArrayList<Player> players = new ArrayList<Player>();
    public final ArrayList<Item> items = new ArrayList<Item>();
    public ArrayList<Resource> resources = new ArrayList<Resource>();
    public final ArrayList<PlaceEffect> effects = new ArrayList<PlaceEffect>(); 
    public TypeOfPlace typeOfPlace;
    public String music;
    public String picture;
    public int altitude;
    private static Map printNewPlaceOnlyOfOneMap;

    public Place(Map map, int positionX, int positionY, int altitude, TypeOfPlace typeOfPlace) {
        if(printNewPlaceOnlyOfOneMap == null)printNewPlaceOnlyOfOneMap = map;
        this.typeOfPlace = typeOfPlace;
        this.map = map;
        this.positionX = positionX;
        this.positionY = positionY;
        this.altitude = altitude;
        music = typeOfPlace.getMusic();
        picture = typeOfPlace.getPicture();
        for(TypeOfResourceWithPressence typeOfResource : typeOfPlace.typeOfResources)
            if(typeOfResource.isHereResource())
                resources.add(new Resource(typeOfResource));
    }

    public String log(){
        String log = "Resources = [ ";
        for(Resource resource : resources) log += resource.typeOfResourceWithPressence.typeOfResource.name + " ";
        if(map == printNewPlaceOnlyOfOneMap) ConsolePrint.gameInfo("Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = " + altitude  + "  " + typeOfPlace.name +  "  " + log + "]");
        return log;
    }

    public int getTemperature() {
        int temperature = 30 - (altitude / 80); // altitude = 1000, ->  30 - (1000 / 80) = 30 - 12 = 8 degrees celsius
        if(map.sky != null){
            Weather weather = map.sky.getWeather(this);
            temperature -= weather.getClouds();
            temperature -= weather.getWeather();
        }
        return temperature;
    }    
}
