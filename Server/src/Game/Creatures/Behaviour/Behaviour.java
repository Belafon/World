package Game.Creatures.Behaviour;

import Game.World;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursProperty;

public abstract class Behaviour {
    protected World game;
    protected int duration;
    protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;
    public BehavioursProperty.Consumable[] consumableProperties;
    public BehavioursProperty[] nonConsumableProperties;

    public Behaviour(World game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
        consumableProperties = new BehavioursProperty.Consumable[0];
        nonConsumableProperties = new BehavioursProperty[0];
    }

    public Behaviour(World game, int duration, int bodyStrain, Creature creature,
            BehavioursProperty.Consumable[] consumableProperties, BehavioursProperty[] nonConsumableProperties) {
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
