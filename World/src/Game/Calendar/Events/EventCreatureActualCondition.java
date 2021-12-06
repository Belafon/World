package Game.Calendar.Events;

import java.util.function.Consumer;

import Game.Game;

public class EventCreatureActualCondition extends Event{
    Consumer<Object> method;

    public EventCreatureActualCondition(int date, Game game, Consumer<Object> method) {
        super(date, game);
        this.method = method;
    }

    @Override
    public void action(Game game) {
        method.accept(null);
    }

    @Override
    public void interrupt(Game game) {        
    }
    
}
