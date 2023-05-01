package com.belafon.world.objectsMemory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleIngredients;

public abstract class Visible implements BehavioursPossibleIngredients{
    public abstract UnboundedPlace getLocation();

    private Set<Creature> watchers = new HashSet<Creature>();
    
    @FunctionalInterface
    public static interface ActionWatcher {
        public void doJob(Set<Creature> watchers);
    }

    /**
     * Unables refactoring and spectating all 
     * watcher of the visible.  
     * @param action
     */
    public void getWatchers(ActionWatcher action) {
        synchronized (watchers) {
            action.doJob(Collections.unmodifiableSet(watchers));
        }
    }
    
    /**
     * Adds new watcher into set of all watchers of the visible.
     * @param creature
     */
    public void addWatcher(Creature creature) {
        synchronized (watchers) {
            watchers.add(creature);
        }
    }

    /**
     * Removes concrete watcher. 
     * It means that, the creature cannot see the
     * visible no longer.
     */
    public void removeWatcher(Creature creature) {
        synchronized (watchers) {
            watchers.remove(creature);
        }
    }
}
