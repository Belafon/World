package com.belafon.world.visibles.creatures.behaviour.behaviours;

import java.util.List;

import com.belafon.world.visibles.creatures.Creature;

/**
 * Labeles object, that could be used for execution some behaviour.
 * It holds array of all possible requirements, that can be satisfied 
 * by this object.
 */
public interface BehavioursPossibleIngredients {
    List<BehavioursPossibleRequirement> getBehavioursPossibleRequirementType(Creature creature);
}
