package Game.Calendar.Events;

import java.util.function.Consumer;

import Game.World;

public class EventCreatureActualCondition extends Event{
    Consumer<Object> method;

    public EventCreatureActualCondition(int date, World game, Consumer<Object> method) {
        super(date, game);
        this.method = method;
    }

    @Override
    public void action(World game) {
        method.accept(null);
    }

    @Override
    public void interrupt(World game) {        
    }
    
}
