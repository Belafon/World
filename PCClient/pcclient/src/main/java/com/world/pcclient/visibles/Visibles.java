package com.world.pcclient.visibles;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.world.pcclient.visibles.creatures.Creature;
import com.world.pcclient.visibles.items.Item;
import com.world.pcclient.visibles.items.Item.Food;
import com.world.pcclient.visibles.resources.ResourceTypes;
import com.world.pcclient.Panels;
import com.world.pcclient.behaviours.Behaviour;
import com.world.pcclient.behaviours.Behaviours;


public class Visibles {
    private final Hashtable<Integer, Item> items = new Hashtable<>();
    private final Hashtable<Integer, Creature> creatures = new Hashtable<>();
    private final Hashtable<Integer, Resource> resources = new Hashtable<>();

    /**
     * It resolves the message from server
     * and tryes to add item into the items list.
     * It means that the player now know about this item.
     * 
     * @param args
     * @param panels
     * @param behaviours
     */
    public void addItem(String[] args, Panels panels, Behaviours behaviours) {
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
                Set<Behaviour> possibleBehaviours = extractPossibleBehavioursFromArgs(behaviours, args[11]);

                item = new Food(id, itemName, regularWeight, visibility, toss, freshness, filling, warm,
                        possibleBehaviours);
                panels.visibleItems.addVisibleTitlePanel(new VisiblePanel(item));
            }
            default ->
                throw new IllegalArgumentException("Invalid class name for item type: " + className);
        }

        items.put(id, item);
    }

    /**
     * 
     * It resolves the message from server
     * and tryes to add creature into the creature list.
     * It means that the player now know about this creature.
     * 
     * @param args
     * @param panels
     */
    public void addCreature(String[] args, Panels panels, Behaviours behaviours) {
        int id = Integer.parseInt(args[2]);
        String name = args[3];
        String appearance = args[4].replaceAll("_", " ");
        switch (args[5]) {
            case "place" -> {
                int mapId = Integer.parseInt(args[6]);
                int positionX = Integer.parseInt(args[7]);
                int positionY = Integer.parseInt(args[8]);
            }
            case "itemPlace" -> {
            }
        }

        Set<Behaviour> possibleBehaviours = extractPossibleBehavioursFromArgs(behaviours, args[9]);

        Creature creature = new Creature(name, id, appearance, possibleBehaviours); 
        panels.visibleCreatures.addVisibleTitlePanel(new VisiblePanel(creature));
        creatures.put(id, creature);

    }

    public static Set<Behaviour> extractPossibleBehavioursFromArgs(Behaviours behaviours, String possibleRequirements) {
        Set<Behaviour> possibleBehaviours = new HashSet<>();
        for (String possibleBehavioursName : possibleRequirements.split(",")) {
            var possibleBehaviour = behaviours.allBehaviors.get(possibleBehavioursName);
            if (possibleBehaviour == null) {
                throw new Error("Extracting possible behaviours from args and unrecognized behaviour found: " + possibleBehaviour);
            } else
                possibleBehaviours.add(possibleBehaviour);
        }
        return possibleBehaviours;
    }

    /**
     * 
     * It resolves the message from server
     * and tryes to add resource into the resource list.
     * It means that the player now know about this resource.
     * 
     * @param args
     * @param panels
     * @param behaviours
     */
    public void addResource(String[] args, Panels panels, Behaviours behaviours) {
        int id = Integer.parseInt(args[2]);
        String name = args[3];
        var type = ResourceTypes.resorceTypes.get(name);
        int mass = Integer.parseInt(args[4]);
        Set<Behaviour> possibleBehaviours = extractPossibleBehavioursFromArgs(behaviours, args[5]);

        Resource resource = new Resource(id, type, mass, possibleBehaviours);
        panels.visibleResources.addVisibleTitlePanel(new VisiblePanel(resource));
        resources.put(id, resource);
    }

    /**
     * Tryes to remove an item, so the player
     * should not know about the item anymore.
     * 
     * @param args
     * @param panels
     */
    public void removeItem(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (items.containsKey(id)) {
            panels.visibleItems.removeVisibleTitlePanel(items.get(id));
        }
    }

    /**
     * Tryes to remove a creature, so the player
     * should not know about the creature anymore.
     * 
     * @param args
     * @param panels
     */
    public void removeCreature(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (creatures.containsKey(id)) {
            panels.visibleCreatures.removeVisibleTitlePanel(creatures.get(id));
        }
        creatures.remove(id);
    }

    /**
     * Tryes to remove an resource, so the player
     * should not know about the resource anymore.
     * 
     * @param args
     * @param panels
     */
    public void removeResource(String[] args, Panels panels) {
        int id = Integer.parseInt(args[2]);
        if (resources.containsKey(id)) {
            panels.visibleResources.removeVisibleTitlePanel(resources.get(id));
        }
        resources.remove(id);
    }
}
