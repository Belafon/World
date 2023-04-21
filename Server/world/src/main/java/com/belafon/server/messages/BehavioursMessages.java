package com.belafon.server.messages;

import com.belafon.world.creatures.behaviour.BehaviourType;

public interface BehavioursMessages{
    public default void newFeasibleBehaviour(BehaviourType behaviourType) {
    }

    public default void removeFeasibleBehaviour(BehaviourType behaviourType) {
    }
    
}
