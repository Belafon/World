package com.belafon.Game.Calendar.Events;

import java.util.function.Consumer;

import com.belafon.Game.World;

public class EventCreatureActualCondition extends Event{
    Consumer<Object> method;

    public EventCreatureActualCondition(long date, World game, Consumer<Object> method) {
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

    public void cancelEvent(World game) {
        game.calendar.remove(this);
    }
    
}