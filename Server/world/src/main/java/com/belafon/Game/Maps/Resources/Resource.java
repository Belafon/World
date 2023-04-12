package com.belafon.Game.Maps.Resources;

import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import com.belafon.Game.Creatures.Behaviour.Behaviours.FindConcreteResource;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.ObjectsMemory.Visible;

public class Resource extends Visible {
    public volatile int durationOfFinding;
    public final TypeOfResource type;
    private volatile int amount; // 100 -> 100% of durationOfFinding, 100 -> regular amount
    public final int id;

    private static int nextId = 0;
    public Resource(TypeOfResource typeOfResource, int amount) {
        this.type = typeOfResource;
        this.amount = amount;
        this.id = nextId++;
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

}