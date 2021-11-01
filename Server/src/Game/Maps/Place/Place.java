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
import Game.Maps.Resources.TypeOfResourceOfTypeOfPlace;
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

    public int getDurationOfFindingOfResourceWhichIsNotHere(TypeOfResource typeOfResource) {
        int sum = 0;
        int i = 0;
        while (i < resourcesSorted.length) {
            if(resourcesSorted[i].typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness <= typeOfResource.conspicuousness)break;
            sum += resourcesSorted[i].typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness; 
            i++;
        }
        return sum - i * typeOfResource.conspicuousness;
    }
    public synchronized void setResourcesDurationOfFinding(Resource resource) throws Exception{
        int lastIndex = Arrays.asList(resourcesSorted).indexOf(resource);
        if(lastIndex == -1) throw new Exception("Value exception");
        
        int newIndex = binarySearchTheClosestLower(resourcesSorted, resource);

        if(lastIndex < newIndex){
            Resource last = resourcesSorted[newIndex - 1];
            resourcesSorted[newIndex - 1] = resource;
            for (int i = newIndex - 2; i >= lastIndex; i--) {
                Resource actual = resourcesSorted[i];
                resourcesSorted[i] = last;
                last = actual;
            }
        }else if(newIndex < lastIndex){
            Resource last = resource;
            for (int i = newIndex; i <= lastIndex - 1; i++) {
                Resource actual = resourcesSorted[i];
                resourcesSorted[i] = last;
                last = actual;
            }
            resourcesSorted[lastIndex] = last;
        } // else do nothing
    }
    private int binarySearchTheClosestLower(Resource[] array, Resource resource) {
        int index = recursion(array, resource, 0, array.length - 1, -1);
        if(resource.getConspicuousness() > array[0].getConspicuousness() || resource.getConspicuousness() < array[array.length - 1].getConspicuousness() || index + 1 > array.length - 1)return index;
        return index + 1;
    }
    private int recursion(Resource[] array, Resource resource, int i, int j, int lastAverage) {
        int average = (i + j) / 2;
        //System.out.println(i + " " + j + "   " + average);
        if(average == lastAverage)return average;
        if(array[average].getConspicuousness() > resource.getConspicuousness())return recursion(array, resource, average + 1, j, average);
        else if(array[average].getConspicuousness() < resource.getConspicuousness())return recursion(array, resource, i, average - 1, average);
        return average;
    }
  
    /* generates resources in the Place, sorts them by conspicuousness and sets duartionOfFinding value of each generated resource*/ 
    private void setResources(TypeOfPlace typeOfPlace2) {
        for(TypeOfResourceOfTypeOfPlace typeOfResource : typeOfPlace.typeOfResources) // TODO needs debug
            if(typeOfResource.isHereResourceGenerate())
        resources.put(typeOfResource.typeOfResource, new Resource(typeOfResource, typeOfResource.startAmount));
        resourcesSorted = resources.values().toArray(new Resource[resources.values().size()]);
        
        if(resourcesSorted.length < 1)return;
        Arrays.sort(resourcesSorted, new Comparator<Resource>(){
            @Override
            public int compare(Resource o1, Resource o2) {
                return o2.getConspicuousness() - o1.getConspicuousness();
            }
        });
        resourcesSorted[0].durationOfFinding = 0;
        int sum = resourcesSorted[0].typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness;
        for (int i = 1; i < resourcesSorted.length; i++) {
            resourcesSorted[i].durationOfFinding = sum - resourcesSorted[i].typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness * i; // sum of differences of consp.
            sum += resourcesSorted[i].typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness;
        }
    }
    public String log(){
        String log = "Resources = [ ";
        for(Resource resource : resources.values()) log += resource.typeOfResourceOfTypeOfPlace.typeOfResource.name + " ";
        if(map == printNewPlaceOnlyOfOneMap) ConsolePrint.gameInfo("Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = " + altitude  + "  " + typeOfPlace.name +  "  " + log + "]");
        return log;
    }

}
