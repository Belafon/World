package Game.Maps.Place;

import java.util.Hashtable;

import Game.Maps.Resources.ListOfAllTypesOfResources;
import Game.Maps.Resources.TypeOfResourceOfTypeOfPlace;
import Game.Maps.Resources.ListOfAllTypesOfResources.NamesOfTypesOfResources;
import likeliness.Dice;

/* Include all TypeOfPlaces */
public class ListOfAllTypesOfPlaces {
    public static Hashtable<NamesOfTypesOfPlaces, TypeOfPlace> typeOfPlaces = new Hashtable<NamesOfTypesOfPlaces, TypeOfPlace>(); 
    public static AList[] typeOfPlacesInTheSpecificAltitude = new AList[5];

    public static void setUpAllTypesOfPlaces(){
        typeOfPlaces.put( NamesOfTypesOfPlaces.forest_leafy,  new TypeOfPlace(NamesOfTypesOfPlaces.forest_leafy, 
            new String[]{"forest_leafy_1", "forest_leafy_2", "forest_leafy_3"}, 
            new String[]{"forest_leafy_1", "forest_leafy_2", "forest_leafy_3"}, new int[]{0, 1, 2, 3},
            new TypeOfResourceOfTypeOfPlace[]{ 
                new TypeOfResourceOfTypeOfPlace(50, 100, ListOfAllTypesOfResources.typesOfResources.get(NamesOfTypesOfResources.blueberry)), 
                new TypeOfResourceOfTypeOfPlace(50, 100, ListOfAllTypesOfResources.typesOfResources.get(NamesOfTypesOfResources.mushrooms)), 
                new TypeOfResourceOfTypeOfPlace(100, 100, ListOfAllTypesOfResources.typesOfResources.get(NamesOfTypesOfResources.treeOak))
            }, 
            2, 0x6dff2e, "Fresh deciduous forest covered with fallen leaves and forest plants."));

        typeOfPlaces.put( NamesOfTypesOfPlaces.meadow,  new TypeOfPlace(NamesOfTypesOfPlaces.meadow, 
            new String[]{"meadow_1", "meadow_2", "meadow_3"}, 
            new String[]{"meadow_1"}, new int[]{0, 1, 2},
            new TypeOfResourceOfTypeOfPlace[0], 
            3, 0xb8fc30, "Meadow abounding with tall grass and full of buzzing insects."));

        typeOfPlaces.put( NamesOfTypesOfPlaces.mountein_meadow,  new TypeOfPlace(NamesOfTypesOfPlaces.mountein_meadow, 
            new String[]{"meadow_4", "meadow_5", "meadow_6"}, 
            new String[]{"meadow_2"}, new int[]{3, 4},
            new TypeOfResourceOfTypeOfPlace[0], 
                2, 0xc6f538, "Mountain slope overgrown with tall grass and mountain aromatic herbs."));

                
        
        typeOfPlaces.put( NamesOfTypesOfPlaces.back_space,  new TypeOfPlace(NamesOfTypesOfPlaces.back_space, 
            new String[]{}, 
            new String[]{},
            new int[]{},
            new TypeOfResourceOfTypeOfPlace[0], 
            2, 0xc6f538, "Smelly musty leather bag."));

        typeOfPlaces.put( NamesOfTypesOfPlaces.leather_bag,  new TypeOfPlace(NamesOfTypesOfPlaces.leather_bag, 
            new String[]{}, 
            new String[]{},
            new int[]{},
            new TypeOfResourceOfTypeOfPlace[0], 
            2, 0xc6f538, "Smelly musty leather bag."));

        for (int i = 0; i < typeOfPlacesInTheSpecificAltitude.length; i++)
            typeOfPlacesInTheSpecificAltitude[i] = new AList();
        // this will assort the types of places by altitude
        for(TypeOfPlace typeOfPlace : typeOfPlaces.values())
            for(int altitude : typeOfPlace.altitudesOfPressence)typeOfPlacesInTheSpecificAltitude[altitude].list.add(typeOfPlace.name);
    }

    public enum NamesOfTypesOfPlaces {
        forest_leafy,
        forest_smrk,
        forest_borovice,
        river,
        stream,
        lake,
        mountain_lake,
        mountain_river,
        mountain_stream,
        mountein_meadow,
        wetland,
        swamp_land,
        moorland,
        meadow,
        cave,
        cliff,
        rock_land,
        plateau_of_bushes,
        
        // names of types of space items
        back_space,
        leather_bag
    }
    
    public static TypeOfPlace getRandomTypeOfPlaceAtAltitude(int altitude){
        Dice dice = new Dice(typeOfPlacesInTheSpecificAltitude[altitude].list.size());
        NamesOfTypesOfPlaces name = typeOfPlacesInTheSpecificAltitude[altitude].list.get(dice.toss() - 1);
        return typeOfPlaces.get(name);
    }
}
    /*
    * 1. 4x4, náhodně podle altitude do 5 kategorií
    *   - 0 - 200 - > louka, bažina, mokřad, forest_borovice, forest_leafy, lake, stream, river
    *   - 200 - 400 - > louka, forest_borovice, forest_leafy, lake, stream, river
    *   - 400 - 600 - > louka, forest_borovice, forest_leafy, stream, river, forest_smrk, cave
    *   - 600 - 800 - > mountein_meadow, louka, forest_borovice, forest_smrk, cave,
    *                 mountain_lake, mountain_river, mountain_stream, cliff, rock_land, moorland
    *   - 800 - 1000 - > mountein_meadow, forest_smrk, cave,
    *                 mountain_lake, mountain_stream, cliff, rock_land */
