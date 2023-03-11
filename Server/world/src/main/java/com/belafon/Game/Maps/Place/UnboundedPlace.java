package com.belafon.Game.Maps.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Player;
import com.belafon.Game.Items.Item;
import com.belafon.Game.Maps.PlaceEffects.PlaceEffect;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Resources.TypeOfResource;
import com.belafon.Game.Maps.Resources.TypeOfResourceOfTypeOfPlace;

public class UnboundedPlace {
    public final ArrayList<Creature> creatures = new ArrayList<Creature>();
    public final ArrayList<Player> players = new ArrayList<Player>();
    public final ArrayList<Item> items = new ArrayList<Item>();
    public Hashtable<TypeOfResource, Resource> resources = new Hashtable<TypeOfResource, Resource>();
    public final ArrayList<PlaceEffect> effects = new ArrayList<PlaceEffect>();
    public TypeOfPlace typeOfPlace;
    public volatile Resource[] resourcesSorted;
    public World game;

    public UnboundedPlace(TypeOfPlace typeOfPlace, World game) {
        this.typeOfPlace = typeOfPlace;
        setStartResources(typeOfPlace);
        this.game = game;
    }

    public void recountResourcesDurationOfFinding() {
        resourcesSorted[0].durationOfFinding = 0;
        int sum = resourcesSorted[0].getConspicuousness();
        for (int i = 1; i < resourcesSorted.length; i++) {
            resourcesSorted[i].durationOfFinding = sum - resourcesSorted[i].getConspicuousness() * i; // sum of differences of consp.
            sum += resourcesSorted[i].getConspicuousness();
        }
    }
    
    // TODO can not be called, when amount of some resource is changed
    public void addResource(TypeOfResource typeOfResource, int amount){
        Resource resource = new Resource(typeOfResource, amount);

        // we have to chack if there is resource with that typeOfResource
        if(resources.containsKey(typeOfResource)){
            ConsolePrint.error_small("Method addResource() was called to add resource with identical type!");
            return;
        }

        // now we need to insert it to the resources array
        resources.put(typeOfResource, resource);
        Resource[] newArray = new Resource[resourcesSorted.length + 1];
        int index = binarySearchTheClosestLower(resourcesSorted, resource);
        for (int i = 0; i < newArray.length; i++)
            if(i < index) newArray[i] = resourcesSorted[i];
            else if(i > index) newArray[i] = resourcesSorted[i - 1];
            else if(i == index) newArray[i] = resource;
        resourcesSorted = newArray;
        recountResourcesDurationOfFinding();
    }

    /* generates resources in the Place, sorts them by conspicuousness and sets duartionOfFinding value of each generated resource*/ 
    protected void setStartResources(TypeOfPlace typeOfPlace2) {
        for(TypeOfResourceOfTypeOfPlace typeOfResource : typeOfPlace.typeOfResources) // TODO needs debug
            if(typeOfResource.isHereResourceGenerate())
        resources.put(typeOfResource.typeOfResource, new Resource(typeOfResource.typeOfResource, typeOfResource.startAmount));
        resourcesSorted = resources.values().toArray(new Resource[resources.values().size()]);
        
        if(resourcesSorted.length < 1)return;
        Arrays.sort(resourcesSorted, new Comparator<Resource>(){
            @Override
            public int compare(Resource o1, Resource o2) {
                return o2.getConspicuousness() - o1.getConspicuousness();
            }
        });
        recountResourcesDurationOfFinding();
    }

    public static int binarySearchTheClosestLower(Resource[] array, Resource resource) {
        if(array.length == 0)return 0;
        int index = binarySearchRecursion(array, resource, 0, array.length - 1, -1);
        if(resource.getConspicuousness() > array[0].getConspicuousness() || resource.getConspicuousness() < array[array.length - 1].getConspicuousness() || index + 1 > array.length - 1)return index;
        return index + 1;
    }
    private static int binarySearchRecursion(Resource[] array, Resource resource, int i, int j, int lastAverage) {
        int average = (i + j) / 2;
        if(average == lastAverage)return average;
        if(array[average].getConspicuousness() > resource.getConspicuousness())return binarySearchRecursion(array, resource, average + 1, j, average);
        else if(array[average].getConspicuousness() < resource.getConspicuousness())return binarySearchRecursion(array, resource, i, average - 1, average);
        return average;
    }
}
