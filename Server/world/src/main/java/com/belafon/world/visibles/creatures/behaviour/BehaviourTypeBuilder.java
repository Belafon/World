package com.belafon.world.visibles.creatures.behaviour;

import java.util.HashMap;
import java.util.Map;

import com.belafon.world.visibles.creatures.behaviour.BehaviourType.IngredientsCounts;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

public class BehaviourTypeBuilder {
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
        if (object.requirements.size() == 0)
            BehaviourType.ALL_BEHAVIOUR_TYPES_WITHOUT_REQUIREMENT.add(object);
        requirements = null;
        return object;
    }
}