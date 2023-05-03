package com.world.pcclient.inventory;

import java.util.Hashtable;
import java.util.Map;

import com.world.pcclient.visibles.items.Item;
import com.world.pcclient.visibles.items.Item.Food;

public class Inventory {
    public Map<Integer, Item> items = new Hashtable<>();

    // TODO add method for adding item, that is already known
    /**
     * Adds an item to the players inventory. 
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
            int toss, String[] args) {
        Item item = switch (type) {
            case "FoodTypeItem" -> new Food(id, name, weight, visiblity, toss, Integer.parseInt(args[7]),
                Integer.parseInt(args[8]), Integer.parseInt(args[9]));
            
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
        items.put(id, item);
    }

    public void removeItem(int id) {
        items.remove(id);
    }   
    
}
