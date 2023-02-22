package Game.Creatures.Behaviour;

import Game.World;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;

public abstract class Behaviour {
    protected World game;
    protected int duration;
    protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;
    public BehavioursPossibleRequirement.Consumable[] consumableProperties;
    public BehavioursPossibleRequirement[] nonConsumableProperties;

    public Behaviour(World game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        consumableProperties = new BehavioursPossibleRequirement.Consumable[0];
        nonConsumableProperties = new BehavioursPossibleRequirement[0];
    }

    public Behaviour(World game, int duration, int bodyStrain, Creature creature,
            BehavioursPossibleRequirement.Consumable[] consumableProperties, BehavioursPossibleRequirement[] nonConsumableProperties) {
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
}
