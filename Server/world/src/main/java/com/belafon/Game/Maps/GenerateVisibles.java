package com.belafon.Game.Maps;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Player;
import com.belafon.Game.Creatures.Races.Animals.Animal;
import com.belafon.Game.Creatures.Races.Animals.AnimalRaces.AnimalRace;
import com.belafon.Game.Items.ListOfAllItemTypes;
import com.belafon.Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import com.belafon.Game.Items.Types.Food;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.Maps.Resources.ListOfAllTypesOfResources;
import com.belafon.Game.Maps.Resources.Resource;

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
