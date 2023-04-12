package com.example.visibles;

import java.util.Hashtable;

import com.example.Panels;
import com.example.visibles.creatures.Creature;
import com.example.visibles.items.Item;
import com.example.visibles.items.Item.Food;

public class Visibles {
    private final Hashtable<Integer, Item> items = new Hashtable<>();
    private final Hashtable<Integer, Creature> creatures = new Hashtable<>();
    private final Hashtable<Integer, Resource> resources = new Hashtable<>();

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void addCreature(Creature creature) {
        creatures.put(creature.getId(), creature);
    }

    public void addResource(Resource resource) {
        resources.put(resource.getId(), resource);
    }

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
        // TODO: Create a new Creature object and add it to the client's view
        Creature creature = new Creature(name, id, appearance);
        panels.visibleCreatures.addVisibleTitlePanel(new VisiblePanel(creature));

    }

    public void addResource(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        String name = args[3];
        Resource resource = new Resource(name, id);
        panels.visibleResources.addVisibleTitlePanel(new VisiblePanel(resource));

        // TODO: Create a new Resource object and add it to the client's view
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public void removeItem(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        items.remove(id);
        // TODO: Remove the item with the given ID from the client's view
    }

    public void removeCreature(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        creatures.remove(id);

        // TODO: Remove the creature with the given ID from the client's view
    }

    public void removeResource(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        resources.remove(id);

        // TODO: Remove the resource with the given name from the client's view
    }
}
