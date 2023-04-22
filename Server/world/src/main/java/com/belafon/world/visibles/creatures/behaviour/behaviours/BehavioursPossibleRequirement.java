package com.belafon.world.visibles.creatures.behaviour.behaviours;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.belafon.world.visibles.creatures.behaviour.BehaviourType;

/** 
 * The child could be required for some behaviour execution. 
*/
public abstract class BehavioursPossibleRequirement {
    /**
     * Each BehaviourPossibleRequirement holds the set of 
     * behaviours, that are dependent on that. The reason is 
     * better performance when creature counts possible
     * behaviours that can be done, when some BehaviourPossibleIngredient
     * changes.
     */
    public Set<BehaviourType> behaviours = new HashSet<>();

    /**
     *  should be called after all BehaviourTypes are created
     */
    public void makeUnmodifiable(){
        behaviours = Collections.unmodifiableSet(behaviours);
    }

}
