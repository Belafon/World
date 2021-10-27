package Game.Maps.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

import Console.ConsolePrint;
import Game.Creatures.Creature;
import Game.Creatures.Player;
import Game.Items.Item;
import Game.Maps.Map;
import Game.Maps.PlaceEffects.PlaceEffect;
import Game.Maps.Resources.Resource;
import Game.Maps.Resources.TypeOfResource;
import Game.Maps.Resources.TypeOfResourceWithPressence;
import Game.Maps.Weather.Weather;

public class Place {
    public final Map map;
    public final int positionX;
    public final int positionY;
    public final ArrayList<Creature> creatures = new ArrayList<Creature>();
    public final ArrayList<Player> players = new ArrayList<Player>();
    public final ArrayList<Item> items = new ArrayList<Item>();
    public Hashtable<TypeOfResource, Resource> resources = new Hashtable<TypeOfResource, Resource>();
    public final ArrayList<PlaceEffect> effects = new ArrayList<PlaceEffect>(); 
    public TypeOfPlace typeOfPlace;
    public String music;
    public String picture;
    public int altitude;
    public volatile int visibility = 0;

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
        setResources(typeOfPlace);
    }

    public volatile Resource[] resourcesSorted;
    private void setResources(TypeOfPlace typeOfPlace2) {
        for(TypeOfResourceWithPressence typeOfResource : typeOfPlace.typeOfResources) // TODO needs debug
            if(typeOfResource.isHereResourceGenerate())
        resources.put(typeOfResource.typeOfResource, new Resource(typeOfResource));
        resourcesSorted = resources.values().toArray(new Resource[resources.values().size()]);
        //ArrayList<Entry<TypeOfResource, Resource>> l = new ArrayList<Entry<TypeOfResource, Resource>>(resources.entrySet());
        if(resourcesSorted.length < 1)return;
        Arrays.sort(resourcesSorted, new Comparator<Resource>(){
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.typeOfResourceWithPressence.typeOfResource.conspicuousness - o2.typeOfResourceWithPressence.typeOfResource.conspicuousness;
            }
        });
        resourcesSorted[0].durationOfFinding = 0;
        int sum = resourcesSorted[0].typeOfResourceWithPressence.typeOfResource.conspicuousness;
        for (int i = 1; i < resourcesSorted.length; i++) {
            resourcesSorted[i].durationOfFinding = sum - resourcesSorted[i].typeOfResourceWithPressence.typeOfResource.conspicuousness * i; // sum of differences of consp.
            sum += resourcesSorted[i].typeOfResourceWithPressence.typeOfResource.conspicuousness;
        }
    }

    public int getTemperature() {
        int temperature = 30 - (altitude / 80) + map.game.time.partOfDay.temperatureChange; // altitude = 1000, ->  30 - (1000 / 80) = 30 - 12 = 8 degrees celsius
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
        for(Resource resource : resources.values()) log += resource.typeOfResourceWithPressence.typeOfResource.name + " ";
        if(map == printNewPlaceOnlyOfOneMap) ConsolePrint.gameInfo("Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = " + altitude  + "  " + typeOfPlace.name +  "  " + log + "]");
        return log;
    }

    public int getDurationOfFindingOfResourceWhichIsNotHere(TypeOfResource typeOfResource) {
        int sum = 0;
        int i = 0;
        while (i < resourcesSorted.length) {
            if(resourcesSorted[i].typeOfResourceWithPressence.typeOfResource.conspicuousness <= typeOfResource.conspicuousness)break;
            sum += resourcesSorted[i].typeOfResourceWithPressence.typeOfResource.conspicuousness; 
            i++;
        }
        return sum - i*typeOfResource.conspicuousness;
    }
}
