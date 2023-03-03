package Game.Creatures.Condition.Knowledge;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

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
