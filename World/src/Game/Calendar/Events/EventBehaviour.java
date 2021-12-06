package Game.Calendar.Events;

import Game.Game;
import Game.Creatures.Behaviour.Behaviour;

public class EventBehaviour extends Event{
    private final Behaviour behaviour;
    public EventBehaviour(int date, Game game, Behaviour behaviour) {
        super(date, game);
        this.behaviour = behaviour;
    }

    @Override
    public void action(Game game) {
        behaviour.cease();
    }

    @Override
    public void interrupt(Game game) {
        behaviour.interrupt(); 
    }
    
}
