package com.belafon.world.visibles.creatures.condition.knowledge;

import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleIngredients;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

public class Knowledge implements BehavioursPossibleIngredients {
    public final TypeKnowledge type;
    public volatile int degree; // stupeň znalosti, degree of knowledge
    public Knowledge(TypeKnowledge type, int degree) {
        this.type = type;
        this.degree = degree;
    }
    @Override
    public BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType() {
        return new BehavioursPossibleRequirement[]{type};
    }
}
