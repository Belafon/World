package Game.Creatures.Behaviour;

import java.util.Collections;
import java.util.Map;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

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