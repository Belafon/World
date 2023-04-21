package com.belafon.world.objectsMemory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.belafon.world.creatures.Creature;
import com.belafon.world.creatures.behaviour.behaviours.BehavioursPossibleIngredients;
import com.belafon.world.maps.place.UnboundedPlace;

public abstract class Visible implements BehavioursPossibleIngredients{
    public abstract UnboundedPlace getLocation();

    private Set<Creature> watchers = new HashSet<Creature>();
    
    @FunctionalInterface
    public static interface ActionWatcher {
        public void doJob(Set<Creature> watchers);
    } 

    public void getWatchers(ActionWatcher action) {
        synchronized (watchers) {
            action.doJob(Collections.unmodifiableSet(watchers));
        }
    }
    
    public void addWatcher(Creature creature) {
        synchronized(watchers){
            watchers.add(creature);
        }
    }

    public void removeWatcher(Creature creature) {
        synchronized (watchers) {
            watchers.remove(creature);
        }
    }
}
