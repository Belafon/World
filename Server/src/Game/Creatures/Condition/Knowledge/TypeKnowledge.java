package Game.Creatures.Condition.Knowledge;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

public class TypeKnowledge extends BehavioursPossibleRequirement {
    public final String name;

    public TypeKnowledge(String name) {
        this.name = name;
    }
}
