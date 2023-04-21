package com.belafon.world.items;

import com.belafon.world.World;
import com.belafon.world.calendar.events.Event;
import com.belafon.world.calendar.events.EventItemChange;
import com.belafon.world.creatures.Creature;
import com.belafon.world.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.items.typeItem.TypeItem;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.objectsMemory.Visible;

public class Item extends Visible {
    public static final BehavioursPossibleRequirement REQUIREMENT = new BehavioursPossibleRequirement() {
    };
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
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public synchronized int changeStats(Event event, World game) {
        return 0;
    }

    @Override
    public UnboundedPlace getLocation() {
        return location;
    }

    @Override
    public BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType() {
        return new BehavioursPossibleRequirement[] { type, REQUIREMENT };
    }

}