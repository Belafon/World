package com.belafon.Game.Creatures.Behaviour;

import java.util.Collections;
import java.util.Map;

import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

/**
 * Each Behaviour has its own Behaviour Type. 
 * It holds info about what is required for
 * behaviours execution
 */
public class BehaviourType {
    public record IngredientsCounts(int consumable, int unconsumable) {}
    public final Map<BehavioursPossibleRequirement, IngredientsCounts> requirements;
    public final Class<? extends Behaviour> behaviourClass; 
    protected BehaviourType(Map<BehavioursPossibleRequirement, IngredientsCounts> requirements,
            Class<? extends Behaviour> behaviourClass) {

        this.behaviourClass = behaviourClass;
        this.requirements = Collections.unmodifiableMap(requirements);
    }
}