package com.example.world.game.inventory;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.example.world.game.Panels;
import com.example.world.game.behaviours.Behaviours;
import com.example.world.game.behaviours.BehavioursFragment;
import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.visibles.Visibles;
import com.example.world.game.visibles.creatures.PlayableCreature;
import com.example.world.game.visibles.items.Clothes;
import com.example.world.game.visibles.items.Item;
import com.example.world.game.visibles.items.Item.Food;

public class Inventory {
    public final Map<Integer, Item> items = new Hashtable<>();
    public final Set<Clothes> clothes = new HashSet<>();
    public final PlayersGear gear = new PlayersGear();

    // TODO add method for adding item, that is already known
    /**
     * Adds an item to the players inventory.
     * 
     * @param id
     * @param type
     * @param name
     * @param weight
     * @param visiblity
     * @param toss
     * @param args
     */
    public void addItem(Panels panels, int id, String type,
            String name, int weight, int visiblity,
            int toss, String[] args, Behaviours behaviours) {
        Set<BehavioursRequirement> possibleBehaviours = Visibles.extractRequirementsFromArgs(behaviours, args[11]);

        Item item = switch (type) {
            case "FoodTypeItem" -> new Food(id, name, weight, visiblity, toss, Integer.parseInt(args[8]),
                    Integer.parseInt(args[9]), Integer.parseInt(args[10]), possibleBehaviours);
            case "CLothesTypeItem" -> new Clothes.Builder()
                    .setId(id)
                    .setName(name)
                    .setWeight(weight)
                    .setVisibility(visiblity)
                    .setToss(toss)
                    .setDescription(args[8])
                    .setPartOfBody(gear.getPartOfBodyByName(args[9]))
                    .setDescription(args[10])
                    .build();
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
        items.put(id, item);
        if(item instanceof Clothes clothes)
            this.clothes.add(clothes);

        PlayableCreature.allIngredients.add(item);
        panels.inventory.addItemToInventory(item);
    }
    
    public void removeItem(Panels panels, int id) {
        Item removeItem = items.get(id);
        PlayableCreature.allIngredients.remove(removeItem);
        items.remove(id);
        if(removeItem instanceof Clothes clothes)
            this.clothes.remove(clothes);

        BehavioursFragment.update(panels.stats.behaviours);
        panels.inventory.removeItemFromInventory(removeItem);
    }
}
