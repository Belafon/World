package com.world.pcclient.visibles;

import java.util.Set;

import com.world.pcclient.behaviours.Behaviour;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public abstract class Visible extends BehavioursPossibleIngredient {
    public final String name;

    public Visible(Set<Behaviour> behaviours, String name) {
        super(behaviours);
        this.name = name;
    }

    /**
     * @return method that should be called 
     * when a title panel in list of visibles
     * is clicked.
     */
    public abstract Runnable getOnTitleClick();

    @Override 
    protected String getName() {
        return name;
    }
}
