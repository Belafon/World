package com.example.world.game.visibles;

import java.util.Set;

import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public abstract class Visible extends BehavioursPossibleIngredient {
    public final String name;

    public Visible(Set<BehavioursRequirement> requirements, String name) {
        super(requirements);
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
