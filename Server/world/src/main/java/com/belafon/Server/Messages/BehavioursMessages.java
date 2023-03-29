package com.belafon.Server.Messages;

import com.belafon.Game.Creatures.Behaviour.BehaviourType;

public interface BehavioursMessages{
    public default void newFeasibleBehaviour(BehaviourType behaviourType) {
    }

    public default void removeFeasibleBehaviour(BehaviourType behaviourType) {
    }
    
}
