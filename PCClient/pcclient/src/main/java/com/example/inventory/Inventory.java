package com.example.inventory;

import java.util.Hashtable;
import java.util.Map;

import com.example.visibles.items.Item;
import com.example.visibles.items.Item.Food;

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
