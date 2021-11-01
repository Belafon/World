package Game.Calendar.Events;

import Game.World;
import Game.Items.Item;

public class EventItemChange extends Event{
    private Item item;
    public EventItemChange(int date, World game, Item item) {
        super(date, game);
        this.item = item;
    }

    @Override
    public void action(World game) {
        int date = item.changeStats(this ,game);
        if(date != 0){
            setDate(date + game.time.getTime());
            game.calendar.add(this);
        }
    }

    @Override
    public void interrupt(World game) {
    }
    
}
