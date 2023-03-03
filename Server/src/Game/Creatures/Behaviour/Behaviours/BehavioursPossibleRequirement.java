package Game.Creatures.Behaviour.Behaviours;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import Game.Creatures.Behaviour.BehaviourType;

public abstract class BehavioursPossibleRequirement {
    public Set<BehaviourType> behaviours = new HashSet<>();

    /**
     *  should be called after all BehaviourTypes are created
     */
    public void makeUnmodifiable(){
        behaviours = Collections.unmodifiableSet(behaviours);
    }

}
