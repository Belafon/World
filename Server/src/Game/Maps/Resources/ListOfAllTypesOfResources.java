package Game.Maps.Resources;

import java.util.Hashtable;

import Game.Maps.PlaceEffects.PlaceEffect;

public class ListOfAllTypesOfResources {
    public static Hashtable<NamesOfTypesOfResources, TypeOfResource> typesOfResources = new Hashtable<NamesOfTypesOfResources, TypeOfResource>(); 
    public static void setUpAllResources(){
        typesOfResources.put(NamesOfTypesOfResources.blueberry, new TypeOfResource("blueberry", new PlaceEffect[0], (short)40));
    }
    public enum NamesOfTypesOfResources{
        blueberry
    } 
}
