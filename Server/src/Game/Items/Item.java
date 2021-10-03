package Game.Items;

import Game.Items.TypeItem.TypeItem;
import Game.Game;
import Game.Calendar.Events.Event;
import Game.Calendar.Events.EventItemChange;
import Game.Creatures.Creature;

public class Item {
    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile EventItemChange eventItemChange;
    public Item(Game game, TypeItem type) {
        this.id = game.ItemId();
        this.type = type;
    }
    public synchronized int changeStats(Event event, Game game) {
        return 0;
    }
}
