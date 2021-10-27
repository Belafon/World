package Game.Creatures.Behaviour;

import Game.Game;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;

public abstract class Behaviour {
    protected Game game;
	protected int duration;
	protected final int bodyStrain;
    protected final Creature creature;
    protected EventBehaviour event;
    public Behaviour(Game game, int duration, int bodyStrain, Creature creature) {
        this.game = game;
        this.duration = duration;
        this.bodyStrain = bodyStrain;
        this.creature = creature;
    }
    public abstract void execute();
    public abstract void interrupt();
	public abstract void cease();
    public abstract String canCreatureDoThis();
}
