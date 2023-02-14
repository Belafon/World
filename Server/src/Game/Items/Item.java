package Game.Items;

import Game.Items.TypeItem.TypeItem;
import Game.Maps.Place.Place;
import Game.ObjectsMemory.Visible;
import Game.World;
import Game.Calendar.Events.Event;
import Game.Calendar.Events.EventItemChange;
import Game.Creatures.Creature;

public class Item implements Visible{
    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile EventItemChange eventItemChange;
    public Place location; // TODO change location, when is moving

    public Item(World game, TypeItem type) {
        this.id = game.ItemId();
        this.type = type;
    }

    public synchronized int changeStats(Event event, World game) {
        return 0;
    }
    
    @Override
    public Place getLocation() {
        return location;
    }
}