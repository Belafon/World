package Game.Creatures.Behaviour;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import Console.ConsolePrint;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

public class BehaviourCondition {
    private Creature creature;
    private final Map<BehavioursPossibleRequirement, ArrayList<BehavioursPossibleIngredients>> behavioursProperties = new ConcurrentHashMap<>();
    private final Set<BehaviourType> feasibleBehaviours = new ConcurrentSkipListSet<>();
    
    public BehaviourCondition(Creature creature) {
        this.creature = creature;
    }
    /**
     * checks wether the creature can do some other behaviour.
     * 
     * This is called when behavioursProperties keys updated,
     * or when new Visible spotted, or lost from sight 
     */
    public void addBehavioursPossibleRequirement(BehavioursPossibleRequirement behavioursPossibleRequirement,
            BehavioursPossibleIngredients behavioursPossibleIngredients) {

        if (behavioursProperties.containsKey(behavioursPossibleRequirement)) {
            behavioursProperties.get(behavioursPossibleRequirement).add(behavioursPossibleIngredients);
        } else {
            ArrayList<BehavioursPossibleIngredients> list = new ArrayList<>();
            list.add(behavioursPossibleIngredients);
            behavioursProperties.put(behavioursPossibleRequirement, list);
        }
        checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdded(behavioursPossibleRequirement);
    }
    
    public void removeBehavioursPossibleRequirement(BehavioursPossibleRequirement behavioursPossibleRequirement,
            BehavioursPossibleIngredients behavioursPossibleIngredients) {

        if (!behavioursProperties.containsKey(behavioursPossibleRequirement)) {
            ConsolePrint.error("Creature.removeBehavioursPossibleRequirement: behavioursPossibleRequirement is not a key in behavioursProperties");
            return;
        }
        
        if (!behavioursProperties.get(behavioursPossibleRequirement).contains(behavioursPossibleIngredients)) {
            ConsolePrint.error("Creature.removeBehavioursPossibleRequirement: behavioursProperties.get(behavioursPossibleRequirement) does not contain behavioursPossibleIngredients");
            return;
        }
    
        // success
        behavioursProperties.get(behavioursPossibleRequirement).remove(behavioursPossibleIngredients);
        checkPossibleBehavioursAfterBehavioursPossibleRequirementRemoved(behavioursPossibleRequirement);
    }
    
    private void checkPossibleBehavioursAfterBehavioursPossibleRequirementRemoved(
            BehavioursPossibleRequirement removedRequirement) {
        for (final BehaviourType behaviourType : removedRequirement.behaviours) {
            if (!canCreatureDoTheBehaviour(behaviourType)) 
                removeUnfeasibleBehaviour(behaviourType);
        }            
    }

    
    /**
     * sends informations to creature if it is 
     * capable to do some new behaviour.  
     * @param newRequirement
     */
    private void checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdded(
            BehavioursPossibleRequirement newRequirement) {

        for (final BehaviourType behaviourType : newRequirement.behaviours) {
            if (canCreatureDoTheBehaviour(behaviourType)) 
                addNewFeasibleBehaviour(behaviourType);
        }

    }
    
    public boolean canCreatureDoTheBehaviour(BehaviourType behaviourType) {
        return behaviourType.requirements.entrySet().stream().filter((x) -> {
            if (behavioursProperties.containsKey(x.getKey())
                    && behavioursProperties.get(x.getKey()).size() >= x.getValue().consumable()
                            + x.getValue().unconsumable())
                return false;
            return true;
        }).count() > 0 ? false : true;
    }
    
    private void addNewFeasibleBehaviour(BehaviourType behaviourType) {
        feasibleBehaviours.add(behaviourType);
        creature.writer.behavioursMessages.newFeasibleBehaviour(behaviourType);
    }

    private void removeUnfeasibleBehaviour(BehaviourType behaviourType) {
        feasibleBehaviours.remove(behaviourType);
        creature.writer.behavioursMessages.removeFeasibleBehaviour(behaviourType);
    }
}
