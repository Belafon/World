package com.world.pcclient.behaviours;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Behaviours {
    public final Map<String, Behaviour> allBehaviors = new Hashtable<>();
    public final Map<BehavioursRequirementNames, BehavioursRequirement> allRequirements = new Hashtable<>();
    public final Set<Behaviour> feasibles = new HashSet<>();
    public final BehavioursPanel listPanel = new BehavioursPanel();

    public enum BehavioursRequirementNames {
        item_visible,
        item_in_inventory,
        food_in_inventory
    }

    public Behaviours() {
        setupBehavioursRequirements();
        setupBehaviors();
    }

    public BehavioursPanel getPanel() {
        return listPanel;
    }

    private void setupBehavioursRequirements() {
        addNewBehavioursRequirement(new BehavioursRequirement(BehavioursRequirementNames.item_visible, "Item is visible."));
        addNewBehavioursRequirement(new BehavioursRequirement(BehavioursRequirementNames.item_in_inventory, "An Item is in inventory."));
        addNewBehavioursRequirement(new BehavioursRequirement(BehavioursRequirementNames.food_in_inventory, "A food is in inventory."));
    }

    private void addNewBehavioursRequirement(BehavioursRequirement behavioursRequirement) {
        allRequirements.put(behavioursRequirement.tag, behavioursRequirement);
    }

    private void setupBehaviors() {
        addNewBehaviour(new Behaviour.BehaviourBuilder("Eat")
                .setName("Eat")
                .setDescription("Lets eat some food...")
                .addRequirement(allRequirements.get(BehavioursRequirementNames.food_in_inventory), true, 1)
                .build());

        addNewBehaviour(new Behaviour.BehaviourBuilder("Move")
                .setName("Move to other place")
                .setDescription("Lets go somewhere...")
                .build());

        addNewBehaviour(new Behaviour.BehaviourBuilder("FindConcreteResource")
                .setName("Find some nature resource")
                .setDescription("Lets find some surrounding resource...")
                .build());

        addNewBehaviour(new Behaviour.BehaviourBuilder("PickUpItem")
                .setName("Pick up an item")
                .setDescription("Lets pick an item up into the inventory...")
                .addRequirement(allRequirements.get(BehavioursRequirementNames.item_in_inventory), true, 1)
                .build());
    }

    private void addNewBehaviour(Behaviour behaviour) {
        allBehaviors.put(behaviour.messagesName, behaviour);
    }

    public void addNewFeasibleBehaviour(String behavioursName) {
        var behav = allBehaviors.get(behavioursName);
        if (behav == null)
            throw new IllegalArgumentException(
                    "Behaviours: addNewFeasibleBehaviour: behaviour name is unknown: " + behavioursName);
        feasibles.add(behav);
        listPanel.addItem(behav);
    }

    public void removeFeasibleBehaviour(String behavioursName) {
        var behav = allBehaviors.get(behavioursName);
        if (behav == null)
            throw new IllegalArgumentException(
                    "Behaviours: addNewFeasibleBehaviour: behaviour name is unknown: " + behavioursName);
        feasibles.remove(behav);
        listPanel.removeItem(behav);
    }
}