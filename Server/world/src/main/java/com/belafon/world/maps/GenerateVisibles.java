package com.belafon.world.maps;

import com.belafon.world.World;
import com.belafon.world.creatures.Player;
import com.belafon.world.creatures.races.Animals.Animal;
import com.belafon.world.creatures.races.Animals.animalRaces.AnimalRace;
import com.belafon.world.items.ListOfAllItemTypes;
import com.belafon.world.items.itemsSpecialStats.SpecialFoodsProperties;
import com.belafon.world.items.types.Food;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.maps.resources.ListOfAllTypesOfResources;
import com.belafon.world.maps.resources.Resource;

public class GenerateVisibles {

    public static void spawnDeerAndAllCreaturesNotices(UnboundedPlace place, World game) {
        Animal deer = new Animal(game, "deer1", place,
                "Nice brown wealthy deer", 5, AnimalRace.deer);
        place.addCreature(deer);
        game.creatures.add(deer);
        synchronized (game.players) {
            for (Player player : game.players) {
                player.addVisibleObject(deer);
            }
        }
    }

    public static void spawnMashroomsAndAllCreaturesNotices(UnboundedPlace place, World game) {
        Resource mushrooms = place.addResource(
                ListOfAllTypesOfResources.typesOfResources
                        .get(ListOfAllTypesOfResources.NamesOfTypesOfResources.mushrooms),
                10);

        synchronized (game.players) {
            for (Player player : game.players) {
                player.addVisibleObject(mushrooms);
            }
        }
    }

    public static void spawnAppleAndAllCreaturesNotices(UnboundedPlace place, World game) {
        Food apple = new Food(game, 0, 0,
                new SpecialFoodsProperties[0],
                ListOfAllItemTypes.foodTypes.get(ListOfAllItemTypes.NamesOfFoodItemTypes.apple),
                null);

        place.addItem(apple);

        synchronized (game.players) {
            for (Player player : game.players) {
                player.addVisibleObject(apple);
            }
        }
    }
}
