package com.belafon.Game.ObjectsMemory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import com.belafon.Game.Maps.Place.UnboundedPlace;

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
