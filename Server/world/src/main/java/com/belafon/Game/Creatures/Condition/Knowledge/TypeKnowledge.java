package com.belafon.Game.Creatures.Condition.Knowledge;

import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

public class TypeKnowledge extends BehavioursPossibleRequirement {
    public final String name;

    public TypeKnowledge(String name) {
        this.name = name;
    }
}
