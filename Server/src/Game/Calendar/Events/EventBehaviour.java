package Game.Calendar.Events;

import Game.World;
import Game.Creatures.Behaviour.Behaviour;

public class EventBehaviour extends Event{
    private final Behaviour behaviour;
    public EventBehaviour(int date, World game, Behaviour behaviour) {
        super(date, game);
        this.behaviour = behaviour;
    }

    @Override
    public void action(World game) {
        behaviour.cease();
    }

    @Override
    public void interrupt(World game) {
        behaviour.interrupt(); 
    }
    
}
