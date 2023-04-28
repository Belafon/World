package com.world.pcclient.inventory;

import java.util.Hashtable;
import java.util.Map;

import com.world.pcclient.visibles.items.Item;
import com.world.pcclient.visibles.items.Item.Food;

public class Inventory {
    public Map<Integer, Item> items = new Hashtable<>();
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
