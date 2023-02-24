package Game.Creatures.Condition.Knowledge;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;

public class Knowledge implements BehavioursPossibleIngredients {
    public final TypeKnowledge type;
    public volatile int degree; // stupeň znalosti, degree of knowledge
    public Knowledge(TypeKnowledge type, int degree) {
        this.type = type;
        this.degree = degree;
    }
}
