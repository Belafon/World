package com.belafon.Game.Creatures.Behaviour;

import java.util.Map;

import com.belafon.Game.World;
import com.belafon.Game.Calendar.Events.EventBehaviour;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Behaviour.BehaviourType.IngredientsCounts;
import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import com.belafon.Game.Creatures.Behaviour.Behaviours.ConsumableBehavioursPossibleRequirement;

public abstract class Behaviour {
    protected World game;
    protected int duration;
    protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;
    public BehavioursPossibleRequirement[] requirements;

    public Behaviour(World game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        requirements = new BehavioursPossibleRequirement[0];
    }

    public Behaviour(World game, int duration, int bodyStrain, Creature creature,
            ConsumableBehavioursPossibleRequirement[] consumableProperties, BehavioursPossibleRequirement[] nonConsumableProperties) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        this.requirements = nonConsumableProperties;
    }

    public abstract void execute();

    public abstract void interrupt();

    public abstract void cease();

    public abstract String canCreatureDoThis();

    public abstract Map<BehavioursPossibleRequirement, IngredientsCounts> getRequirements();


    public abstract BehaviourType getType();
}
