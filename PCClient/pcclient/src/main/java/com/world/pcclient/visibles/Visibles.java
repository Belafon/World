package com.world.pcclient.visibles;

import java.util.Hashtable;

import com.world.pcclient.visibles.creatures.Creature;
import com.world.pcclient.visibles.items.Item;
import com.world.pcclient.visibles.items.Item.Food;
import com.world.pcclient.visibles.resources.ResourceTypes;
import com.world.pcclient.Panels;

public class Visibles {
    private final Hashtable<Integer, Item> items = new Hashtable<>();
    private final Hashtable<Integer, Creature> creatures = new Hashtable<>();
    private final Hashtable<Integer, Resource> resources = new Hashtable<>();

    public void addItem(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        String className = args[3];
        String itemName = args[4];
        int regularWeight = Integer.parseInt(args[5]);
        int visibility = Integer.parseInt(args[6]);
        int toss = Integer.parseInt(args[7]);

        Item item = null;
        switch (className) {
            case "FoodTypeItem" -> {
                int freshness = Integer.parseInt(args[8]);
                int filling = Integer.parseInt(args[9]);
                int warm = Integer.parseInt(args[10]);
                item = new Food(id, itemName, regularWeight, visibility, toss, freshness, filling, warm);
                panels.visibleItems.addVisibleTitlePanel(new VisiblePanel(item));
            }
            default ->
                throw new IllegalArgumentException("Invalid class name for item type: " + className);
        }

        items.put(id, item);
    }

    public void addCreature(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        String name = args[3];
        String appearance = args[4].replaceAll("_", " ");
        switch (args[5]) {
            case "place" -> {
                int mapId = Integer.parseInt(args[6]);
                int positionX = Integer.parseInt(args[7]);
                int positionY = Integer.parseInt(args[8]);
            }
            case "itemPlace" -> {}
        }
        Creature creature = new Creature(name, id, appearance);
        panels.visibleCreatures.addVisibleTitlePanel(new VisiblePanel(creature));
        creatures.put(id, creature);

    }

    public void addResource(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        String name = args[3];
        var type = ResourceTypes.resorceTypes.get(name);
        int mass = Integer.parseInt(args[4]);
        Resource resource = new Resource(id, type, mass);
        panels.visibleResources.addVisibleTitlePanel(new VisiblePanel(resource));
        resources.put(id, resource);
    }

    public void removeItem(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (items.containsKey(id)) {
            panels.visibleItems.removeVisibleTitlePanel(items.get(id));
        } 
    }

    public void removeCreature(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (creatures.containsKey(id)) {
            panels.visibleCreatures.removeVisibleTitlePanel(creatures.get(id));
        }
        creatures.remove(id);
    }

    public void removeResource(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (resources.containsKey(id)) {
            panels.visibleResources.removeVisibleTitlePanel(resources.get(id));
        }
        resources.remove(id);
    }
}
