package Game.Calendar.Events;

import Game.Game;
import Game.Time.Clocks;
import Game.Time.Time;

public abstract class Event implements Comparable<Event>{
    private int date;
	protected final int id;

    public Event(int date, Game game) {
		this.id = game.getNewEventId();
		this.date = date;
	}
    public abstract void action(Game game);
	public abstract void interrupt(Game game);

    public int getDate() {
		return date;
	}
	public synchronized void setDate(int dateOfAction) {
		this.date = dateOfAction;
	}
	public int getId() {
		return id;
	}

	@Override
    public int compareTo(Event event) {
		return this.getDate() - event.getDate();
    }
    public int getTimeToWait(Clocks clocks, Time time) {
        return clocks.ticksToMillis(date - time.getTime()) + ((date - time.getTime()) / 12);
    }
}
