package com.belafon.world.visibles.creatures.behaviour.behaviours;

/**
 * Labeles object, that could be used for execution some behaviour.
 * It holds array of all possible requirements, that can be satisfied 
 * by this object.
 */
public interface BehavioursPossibleIngredients {
    BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType();
}
