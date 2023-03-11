package com.belafon.Game.Calendar;

import com.belafon.Game.World;
import com.belafon.Game.Calendar.Events.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Calendar {
    private World game;
    public Calendar(World game){
        this.game = game;
    }
    public PriorityBlockingQueue<Event> heap = new PriorityBlockingQueue<Event>();

    public void add(Event event){
        heap.add(event);
        if((heap.size() == 1 || event == heap.peek()) && game.loop.loopThread != null)
            game.loop.loopThread.interrupt();
    }
    public void remove(Event event){
        Event peekedEvent = heap.peek();
        heap.remove(event);
        if(event == peekedEvent)
            game.loop.loopThread.interrupt();
        event.interrupt(game);
    }
    public void check(){
        Event event = heap.peek();
        //if(event.getDate() < game.time.getTime()){
        heap.remove(event);
        event.action(game);
        //} else return false;
    }

    public Event getNextEvent() {
        return heap.peek();
    }
}
