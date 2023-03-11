package com.belafon.Game.Maps.Resources;

import java.util.Hashtable;

import com.belafon.Game.Maps.PlaceEffects.PlaceEffect;

public class ListOfAllTypesOfResources {
    public static Hashtable<NamesOfTypesOfResources, TypeOfResource> typesOfResources = new Hashtable<NamesOfTypesOfResources, TypeOfResource>(); 
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
