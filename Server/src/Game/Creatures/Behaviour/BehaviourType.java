package Game.Creatures.Behaviour;

import java.util.Collections;
import java.util.Map;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Creatures.Behaviour.Behaviours.ConsumableBehavioursPossibleRequirement;

public class BehaviourType {
    public final Map<BehavioursPossibleRequirement, Integer> requirements;
    public final Map<ConsumableBehavioursPossibleRequirement, Integer> consumableRequirements;
    public final Class<? extends Behaviour> behaviourClass; 
    protected BehaviourType(Map<BehavioursPossibleRequirement, Integer> requirements,
            Map<ConsumableBehavioursPossibleRequirement, Integer> consumableRequirements,
            Class<? extends Behaviour> behaviourClass) {

        this.behaviourClass = behaviourClass;
        this.requirements = Collections.unmodifiableMap(requirements);
        this.consumableRequirements = Collections.unmodifiableMap(consumableRequirements);
    }
    
}