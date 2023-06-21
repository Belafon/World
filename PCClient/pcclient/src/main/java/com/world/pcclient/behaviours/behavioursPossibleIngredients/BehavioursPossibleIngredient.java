package com.world.pcclient.behaviours.behavioursPossibleIngredients;

import java.util.Collections;
import java.util.Set;

import com.world.pcclient.behaviours.BehavioursRequirement;

public abstract class BehavioursPossibleIngredient {
    public final Set<BehavioursRequirement> requirements;

    public BehavioursPossibleIngredient(Set<BehavioursRequirement> requirements) {
        this.requirements = Collections.unmodifiableSet(requirements);
    }

    public String toString() {
        return getName() + " " + getId();
    }

    protected abstract String getName();
    public abstract String getId();

    public String getMessageId(){
        return "" + getId();
    }
    public abstract String getVisibleType();
}
