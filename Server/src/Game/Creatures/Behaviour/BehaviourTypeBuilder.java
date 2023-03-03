package Game.Creatures.Behaviour;

import java.util.HashMap;
import java.util.Map;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Creatures.Behaviour.Behaviours.ConsumableBehavioursPossibleRequirement;


public class BehaviourTypeBuilder{
    private Map<BehavioursPossibleRequirement, Integer> requirements = new HashMap<>();
    private Map<ConsumableBehavioursPossibleRequirement, Integer> consumableRequirements = new HashMap<>();
    private BehaviourType object;

    
    public BehaviourTypeBuilder(Class<? extends Behaviour> behaviourClass) {
        object = new BehaviourType(requirements, consumableRequirements, behaviourClass);
    }

    public BehaviourTypeBuilder addRequirement(BehavioursPossibleRequirement req, int number) {
        requirements.put(req, number);
        req.behaviours.add(object);
        return this;
    }

    public BehaviourTypeBuilder addConsumableRequirement(ConsumableBehavioursPossibleRequirement req, int number) {
        consumableRequirements.put(req, number);
        req.behaviours.add(object);
        return this;
    }

    public BehaviourType build() {
        requirements = null;
        consumableRequirements = null;
        return object;
    }
}