package com.belafon.Game.Creatures.Behaviour;

import java.util.HashMap;
import java.util.Map;

import com.belafon.Game.Creatures.Behaviour.BehaviourType.IngredientsCounts;
import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;


public class BehaviourTypeBuilder{
    private Map<BehavioursPossibleRequirement, IngredientsCounts> requirements = new HashMap<>();
    private BehaviourType object;

    
    public BehaviourTypeBuilder(Class<? extends Behaviour> behaviourClass) {
        object = new BehaviourType(requirements, behaviourClass);
    }

    public BehaviourTypeBuilder addRequirement(BehavioursPossibleRequirement req, IngredientsCounts number) {
        requirements.put(req, number);
        req.behaviours.add(object);
        return this;
    }

    public BehaviourType build() {
        requirements = null;
        return object;
    }
}