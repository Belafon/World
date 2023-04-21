package com.belafon.world.creatures.behaviour;

import java.util.Map;

import com.belafon.world.World;
import com.belafon.world.calendar.events.EventBehaviour;
import com.belafon.world.creatures.Creature;
import com.belafon.world.creatures.behaviour.BehaviourType.IngredientsCounts;
import com.belafon.world.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

/**
 * Represents some action, which can be executed by a creature.
 */
public abstract class Behaviour {
    protected World game;
    protected int duration;
    protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;

    public Behaviour(World game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
    }

    public abstract void execute();

    public abstract void interrupt();

    public abstract void cease();

    public abstract String canCreatureDoThis();

    public abstract Map<BehavioursPossibleRequirement, IngredientsCounts> getRequirements();


    public abstract BehaviourType getType();
}
