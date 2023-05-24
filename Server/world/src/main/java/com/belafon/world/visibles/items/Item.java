package com.belafon.world.visibles.items;

import java.util.Arrays;
import java.util.List;

import com.belafon.world.World;
import com.belafon.world.calendar.events.Event;
import com.belafon.world.calendar.events.EventItemChange;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.objectsMemory.Visible;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.visibles.items.typeItem.TypeItem;

public class Item extends Visible {
    public static final BehavioursPossibleRequirement REQUIREMENT_IS_VISIBLE = new BehavioursPossibleRequirement("An item is visible") {
    };
    public static final BehavioursPossibleRequirement REQUIREMENT_IS_IN_INVENTORY = new BehavioursPossibleRequirement("An item is in inventory") {
    };
    public final BehavioursPossibleRequirement requirementIsVisible;
    public final BehavioursPossibleRequirement requirementIsInInventory;

    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile EventItemChange eventItemChange;
    public UnboundedPlace location; // TODO change location, when is moving
    private int weight;

    public Item(World game, TypeItem type, UnboundedPlace location) {
        this.id = game.visibleIds.getItemId();
        this.type = type;
        this.weight = type.regularWeight;
        this.location = location;
        requirementIsVisible = new BehavioursPossibleRequirement("item " + id + " is visible") {
        };
        requirementIsInInventory = new BehavioursPossibleRequirement("item " + id + " is in inventory") {
        };

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Counts duration to next event, when stats should be updated
     */
    public synchronized int changeStats(Event event, World game) {
        return 0;
    }

    @Override
    public UnboundedPlace getLocation() {
        return location;
    }

    @Override
    public List<BehavioursPossibleRequirement> getBehavioursPossibleRequirementType(Creature creature) {
        if (creature.inventory.ownsItem(this)) {
            return Arrays.asList(type.requirementIsInInventory, requirementIsInInventory, REQUIREMENT_IS_IN_INVENTORY);
        } else {
            return Arrays.asList(type.requirementIsVisible, requirementIsVisible, REQUIREMENT_IS_VISIBLE);
        }
    }

    @Override
    public int getVisibility() {
        return type.visibility;
    }
}