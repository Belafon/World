package Server.Messages;

import Game.Creatures.Behaviour.BehaviourType;

public abstract class BehavioursMessages{
    public abstract void newFeasibleBehaviour(BehaviourType behaviourType);

    public abstract void removeFeasibleBehaviour(BehaviourType behaviourType);
}
