package Game.Calendar.Events;

import Game.Game;
import Game.Items.Item;

public class EventItemChange extends Event{
    private Item item;
    public EventItemChange(int date, Game game, Item item) {
        super(date, game);
        this.item = item;
    }

    @Override
    public void action(Game game) {
        int date = item.changeStats(this ,game);
        if(date != 0){
            setDate(date + game.time.getTime());
            game.calendar.add(this);
        }
    }

    @Override
    public void interrupt(Game game) {
    }
    
}
