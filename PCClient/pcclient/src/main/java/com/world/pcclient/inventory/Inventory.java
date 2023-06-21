package com.world.pcclient.inventory;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.world.pcclient.Panels;
import com.world.pcclient.behaviours.Behaviours;
import com.world.pcclient.behaviours.BehavioursRequirement;
import com.world.pcclient.visibles.Visibles;
import com.world.pcclient.visibles.creatures.PlayableCreature;
import com.world.pcclient.visibles.items.Item;
import com.world.pcclient.visibles.items.Item.Food;

public class Inventory {
    public Map<Integer, Item> items = new Hashtable<>();

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
    public void addItem(int id, String type,
            String name, int weight, int visiblity,
            int toss, String[] args, Behaviours behaviours) {
        Set<BehavioursRequirement> possibleBehaviours = Visibles.extractRequirementsFromArgs(behaviours, args[11]);

        Item item = switch (type) {
            case "FoodTypeItem" -> new Food(id, name, weight, visiblity, toss, Integer.parseInt(args[8]),
                    Integer.parseInt(args[9]), Integer.parseInt(args[10]), possibleBehaviours);

            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
        items.put(id, item);
        PlayableCreature.allIngredients.add(item);
    }
    
    public void removeItem(Panels panels, int id) {
        PlayableCreature.allIngredients.remove(items.get(id));
        items.remove(id);
        panels.behaviours.update(panels.stats.behaviours);
    }

}
