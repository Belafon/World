package com.world.pcclient.behaviours.behavioursPossibleIngredients;

import java.util.Collections;
import java.util.Set;

import com.world.pcclient.behaviours.Behaviour;

public abstract class BehavioursPossibleIngredient {
    public final Set<Behaviour> behaviours;

    public BehavioursPossibleIngredient(Set<Behaviour> behaviours) {
        this.behaviours = Collections.unmodifiableSet(behaviours);
    }

    public String toString(){
        return getName();
    }

    protected abstract String getName();
}
