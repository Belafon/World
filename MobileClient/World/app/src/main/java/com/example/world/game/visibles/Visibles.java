package com.example.world.game.visibles;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import com.example.world.game.visibles.creatures.Creature;
import com.example.world.game.visibles.creatures.PlayableCreature;
import com.example.world.game.visibles.items.Item;
import com.example.world.game.visibles.items.Item.Food;
import com.example.world.game.visibles.items.ItemInfoFragment;
import com.example.world.game.visibles.resources.ResourceTypes;
import com.example.world.game.Panels;
import com.example.world.game.behaviours.Behaviours;
import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.maps.Place;

public class Visibles {
    private final Hashtable<Integer, Item> items = new Hashtable<>();
    private final Hashtable<Integer, Creature> creatures = new Hashtable<>();
    private final Hashtable<Integer, Resource> resources = new Hashtable<>();
    private final Hashtable<String, TypeOfResource> typesOfresources = new Hashtable<>();
    private final Set<Place> places = new HashSet<>();

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
                Set<BehavioursRequirement> requirements = extractRequirementsFromArgs(behaviours, args[11]);

                item = new Food(id, itemName, regularWeight, visibility, toss, freshness, filling, warm,
                        requirements);
                panels.visibleItems.addVisiblesTitle(item);
                PlayableCreature.allIngredients.add(item);
            }
            default ->
                throw new IllegalArgumentException("Invalid class name for item type: " + className);
        }
        synchronized (items) {
            items.put(id, item);
        }
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
        // TODO arg[9] ... current behaviour
        Set<BehavioursRequirement> requirements = extractRequirementsFromArgs(behaviours, args[10]);

        Creature creature = new Creature(name, id, appearance, requirements);
        panels.visibleCreatures.addVisiblesTitle(creature);
        synchronized (creatures) {
            creatures.put(id, creature);
        }

        
        synchronized (PlayableCreature.allIngredients) {
            PlayableCreature.allIngredients.add(creature);
        }

    }

    public static Set<BehavioursRequirement> extractRequirementsFromArgs(Behaviours behaviours,
            String possibleRequirements) {
        Set<BehavioursRequirement> requirements = new HashSet<>();
        for (String possibleRequirementsName : possibleRequirements.split(",")) {
            var possibleRequirement = behaviours.allRequirements.get(possibleRequirementsName);
            if (possibleRequirement == null) {
                // add new requirement into the list of all requirements
                possibleRequirement = new BehavioursRequirement(possibleRequirementsName, possibleRequirementsName);
                behaviours.allRequirements.put(possibleRequirementsName, possibleRequirement);
                requirements.add(possibleRequirement);

                throw new Error("Extracting requirements from args and unrecognized behaviour found: "
                        + possibleRequirementsName);
            } else
                requirements.add(possibleRequirement);
        }
        return requirements;
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
        Set<BehavioursRequirement> requirements = extractRequirementsFromArgs(behaviours, args[5]);

        Resource resource = new Resource(id, type, mass, requirements);
        panels.visibleResources.addVisiblesTitle(resource);
        synchronized (resources) {
            resources.put(id, resource);
        }

        synchronized (PlayableCreature.allIngredients) {
            PlayableCreature.allIngredients.add(resource);
        }
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
            var item = items.get(id);
            panels.visibleItems.removeVisiblesTitle(item);
            
            PlayableCreature.allIngredients.remove(item);
            
            panels.behaviours.update(panels.stats.behaviours);
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
            panels.visibleCreatures.removeVisiblesTitle(creatures.get(id));
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
            panels.visibleResources.removeVisiblesTitle(resources.get(id));
        }
        synchronized (resources) {
            resources.remove(id);
        }
    }

    public void addNewResourceType(String[] args, Behaviours behaviours) {
        String idName = args[2];
        String name = args[3];
        String requirementsMessage = args[4];
        Set<BehavioursRequirement> requirements = extractRequirementsFromArgs(behaviours, requirementsMessage);
        synchronized (typesOfresources) {
            typesOfresources.put(idName, new TypeOfResource(name, idName, requirements));
        }

        synchronized (PlayableCreature.allIngredients) {
            PlayableCreature.allIngredients.add(typesOfresources.get(idName));
        }
    }

    public void addNewPlace(Place place) {
        synchronized (PlayableCreature.allIngredients) {
            PlayableCreature.allIngredients.add(place);
        }

        synchronized (places) {
            places.add(place);
        }
    }

    public void removePlace(Place place) {
        synchronized (places) {
            places.remove(place);
        }
        PlayableCreature.allIngredients.remove(place);
    }
}
