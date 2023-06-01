package com.belafon.server.messages;

import com.belafon.world.visibles.creatures.behaviour.BehaviourType;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

public interface BehavioursMessages{
    public default void newFeasibleBehaviour(BehaviourType behaviourType) {
    }

    public default void removeFeasibleBehaviour(BehaviourType behaviourType) {
    }

    public default void setupBehaviour(BehaviourType behaviourType) {
    }

    public default void setupPossibleReqirement(BehavioursPossibleRequirement requirement) {
    }
    
}
