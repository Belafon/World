package com.belafon.world.maps.resources;

import com.belafon.world.World;
import com.belafon.world.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.creatures.behaviour.behaviours.FindConcreteResource;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.objectsMemory.Visible;

public class Resource extends Visible {
    public volatile int durationOfFinding;
    public final TypeOfResource type;
    private volatile int amount; // 100 -> 100% of durationOfFinding, 100 -> regular amount
    public final int id;

    public Resource(TypeOfResource typeOfResource, int amount, World game) {
        this.type = typeOfResource;
        this.amount = amount;
        this.id = game.visibleIds.getResourceId();
    }

    public int getConspicuousness() {
        return (type.conspicuousness * amount) / 100;
    }

    public void setAmount(int amount, UnboundedPlace place) {
        this.amount = amount;
        try {
            FindConcreteResource.setResourcesDurationOfFinding(place, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UnboundedPlace getLocation() {
        return null;
    }

    @Override
    public BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType() {
        return new BehavioursPossibleRequirement[] { type };
    }

    public int getAmount() {
        return amount;
    }

}