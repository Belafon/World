package com.belafon.Game.Calendar.Events;

import com.belafon.Game.World;
import com.belafon.Game.Time.Clocks;
import com.belafon.Game.Time.Time;

public abstract class Event implements Comparable<Event>{
    private long date;
	protected final int id;

    public Event(long date, World game) {
		this.id = game.getNewEventId();
		this.date = date;
	}
    public abstract void action(World game);
	public abstract void interrupt(World game);

    public long getDate() {
		return date;
	}
	public synchronized void setDate(long dateOfAction) {
		this.date = dateOfAction;
	}
	public int getId() {
		return id;
	}

	@Override
    public int compareTo(Event event) {
        return this.getDate() < event.getDate() ? -1 : 
            this.getDate() == event.getDate() ? 0 : 1;
    }
    public long getTimeToWait(Clocks clocks, Time time) {
        return clocks.ticksToMillis(date - time.getTime()) + ((date - time.getTime()) / 12);
    }
}
