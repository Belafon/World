package Game.Creatures.Behaviour;

import java.util.Map;

import Game.World;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Creatures.Behaviour.Behaviours.ConsumableBehavioursPossibleRequirement;

public abstract class Behaviour {
    protected World game;
    protected int duration;
    protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;
    public ConsumableBehavioursPossibleRequirement[] consumableProperties;
    public BehavioursPossibleRequirement[] nonConsumableProperties;

    public Behaviour(World game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        consumableProperties = new ConsumableBehavioursPossibleRequirement[0];
        nonConsumableProperties = new BehavioursPossibleRequirement[0];
    }

    public Behaviour(World game, int duration, int bodyStrain, Creature creature,
            ConsumableBehavioursPossibleRequirement[] consumableProperties, BehavioursPossibleRequirement[] nonConsumableProperties) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        this.consumableProperties = consumableProperties;
        this.nonConsumableProperties = nonConsumableProperties;
    }

    public abstract void execute();

    public abstract void interrupt();

    public abstract void cease();

    public abstract String canCreatureDoThis();

    public abstract Map<BehavioursPossibleRequirement, Integer> getRequirements();

    public abstract Map<ConsumableBehavioursPossibleRequirement, Integer> getConsumableRequirements();


    public abstract BehaviourType getType();
}
