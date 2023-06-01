package com.belafon.world.visibles.creatures.behaviour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.belafon.console.ConsolePrint;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleIngredients;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

public class BehaviourCondition {
    private Creature creature;
    private final Map<BehavioursPossibleRequirement, ArrayList<BehavioursPossibleIngredients>> behavioursIngredients = new ConcurrentHashMap<>();
    private final Set<BehaviourType> feasibleBehaviours = new HashSet<>();

    public BehaviourCondition(Creature creature) {
        this.creature = creature;
    }

    public void sendInfoAboutAllBehavioursWithNoRequirements(){
        feasibleBehaviours.addAll(BehaviourType.ALL_BEHAVIOUR_TYPES_WITHOUT_REQUIREMENT);
        for (BehaviourType behaviourType : feasibleBehaviours)
            creature.writer.behavioursMessages.newFeasibleBehaviour(behaviourType);
    }

    /**
     * checks wether the creature can do some other behaviour.
     * 
     * This is called when behavioursProperties keys updated,
     * or when new Visible spotted, or lost from sight
     */
    public void addBehavioursPossibleIngredient(BehavioursPossibleRequirement behavioursPossibleRequirement,
            BehavioursPossibleIngredients behavioursPossibleIngredients) {

        if (behavioursIngredients.containsKey(behavioursPossibleRequirement)) {
            behavioursIngredients.get(behavioursPossibleRequirement).add(behavioursPossibleIngredients);
        } else {
            ArrayList<BehavioursPossibleIngredients> list = new ArrayList<>();
            list.add(behavioursPossibleIngredients);
            behavioursIngredients.put(behavioursPossibleRequirement, list);
        }
        checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdded(behavioursPossibleRequirement);
    }

    public void removeBehavioursPossibleRequirement(BehavioursPossibleRequirement requirement,
            BehavioursPossibleIngredients ingredient) {

        if (!behavioursIngredients.containsKey(requirement)) {
            ConsolePrint.error(
                    "Creature.removeBehavioursPossibleRequirement: behavioursPossibleRequirement is not a key in behavioursProperties");
            return;
        }

        if (!behavioursIngredients.get(requirement).contains(ingredient)) {
            ConsolePrint.error(
                    "Creature.removeBehavioursPossibleRequirement: behavioursProperties.get(behavioursPossibleRequirement) does not contain behavioursPossibleIngredients");
            return;
        }

        // success
        behavioursIngredients.get(requirement).remove(ingredient);
        checkPossibleBehavioursAfterBehavioursPossibleRequirementRemoved(requirement);
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
     * 
     * @param newRequirement
     */
    private void checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdded(
            BehavioursPossibleRequirement newRequirement) {

        for (final BehaviourType behaviourType : newRequirement.behaviours) {
            if (canCreatureDoTheBehaviour(behaviourType)) {
                synchronized (feasibleBehaviours) {
                    if (feasibleBehaviours.contains(behaviourType))
                        continue;
                }
                addNewFeasibleBehaviour(behaviourType);
            }
        }

    }

    public boolean canCreatureDoTheBehaviour(BehaviourType behaviourType) {
        return behaviourType.requirements.entrySet().stream().filter((x) -> {
            if (behavioursIngredients.containsKey(x.getKey())
                    && behavioursIngredients.get(x.getKey()).size() >= x.getValue().numOfSpecificIngredients()
                            + x.getValue().numOfGeneralIngredients())
                return false;
            return true;
        }).count() > 0 ? false : true;
    }

    private void addNewFeasibleBehaviour(BehaviourType behaviourType) {
        synchronized (feasibleBehaviours) {
            feasibleBehaviours.add(behaviourType);
        }
        creature.writer.behavioursMessages.newFeasibleBehaviour(behaviourType);
    }

    private void removeUnfeasibleBehaviour(BehaviourType behaviourType) {
        synchronized (feasibleBehaviours) {
            feasibleBehaviours.remove(behaviourType);
        }
        creature.writer.behavioursMessages.removeFeasibleBehaviour(behaviourType);
    }
}
