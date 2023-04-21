package com.belafon.world.creatures.behaviour.behaviours;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.belafon.world.creatures.behaviour.BehaviourType;

/** 
 * The child could be required for some behaviour execution. 
*/
public abstract class BehavioursPossibleRequirement {
    public Set<BehaviourType> behaviours = new HashSet<>();

    /**
     *  should be called after all BehaviourTypes are created
     */
    public void makeUnmodifiable(){
        behaviours = Collections.unmodifiableSet(behaviours);
    }

}
