package com.belafon.Game.Items;

import com.belafon.Game.Items.TypeItem.TypeItem;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.ObjectsMemory.Visible;
import com.belafon.Game.World;
import com.belafon.Game.Calendar.Events.Event;
import com.belafon.Game.Calendar.Events.EventItemChange;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

public class Item implements Visible {
    public static final BehavioursPossibleRequirement REQUIREMENT = new BehavioursPossibleRequirement(){};
    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile EventItemChange eventItemChange;
    public UnboundedPlace location; // TODO change location, when is moving
    private int weight;

    
    public Item(World game, TypeItem type) {
        this.id = game.ItemId();
        this.type = type;
        this.weight = type.regularWeight;
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
        return new BehavioursPossibleRequirement[]{type, REQUIREMENT};
    }
}