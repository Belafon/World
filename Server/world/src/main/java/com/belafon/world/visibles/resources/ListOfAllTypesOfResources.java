package com.belafon.world.visibles.resources;

import java.util.Hashtable;

import com.belafon.world.maps.placeEffects.PlaceEffect;

public class ListOfAllTypesOfResources {
    public static Hashtable<NamesOfTypesOfResources, TypeOfResource> typesOfResources = new Hashtable<NamesOfTypesOfResources, TypeOfResource>();
    
    /**
     * There are defined all types of resources in the game.
     */
    public static void setUpAllResources(){
        typesOfResources.put(NamesOfTypesOfResources.blueberry, new TypeOfResource(NamesOfTypesOfResources.blueberry, new PlaceEffect[0], 1000));
        typesOfResources.put(NamesOfTypesOfResources.mushrooms, new TypeOfResource(NamesOfTypesOfResources.mushrooms, new PlaceEffect[0], 996));
        typesOfResources.put(NamesOfTypesOfResources.treeOak, new TypeOfResource(NamesOfTypesOfResources.treeOak, new PlaceEffect[0], 1010));
        typesOfResources.put(NamesOfTypesOfResources.treePine, new TypeOfResource(NamesOfTypesOfResources.treePine, new PlaceEffect[0], 1010));
        typesOfResources.put(NamesOfTypesOfResources.treeSpruce, new TypeOfResource(NamesOfTypesOfResources.treeSpruce, new PlaceEffect[0], 1010));
    }
    public enum NamesOfTypesOfResources{
        blueberry,
        mushrooms,
        treeOak, // dub
        treePine,
        treeSpruce
    }
}
