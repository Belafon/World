package com.belafon.world.creatures.condition.knowledge;

import com.belafon.world.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

public class TypeKnowledge extends BehavioursPossibleRequirement {
    public final String name;

    public TypeKnowledge(String name) {
        this.name = name;
    }
}
