package com.belafon.world.visibles.creatures.behaviour;

import java.util.Map;

import com.belafon.world.World;
import com.belafon.world.calendar.events.EventBehaviour;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.BehaviourType.IngredientsCounts;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;

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

    public void interrupt(){
    }

    public void cease() {
    }

    /**
     * It is used to check if creature can still execute the behavior.
     * It is veifies the message from clent, if it is not trying to do 
     * someting illegal.
     * @return Returns null, if the creature cannot execute the.
     * Ohterwise it returns message that says what is wrong,
     * why the creature cannot execute this behaviour.
     */
    public String canCreatureDoThis() {
        return null;
    }

    public abstract Map<BehavioursPossibleRequirement, IngredientsCounts> getRequirements();


    public abstract BehaviourType getType();
}
