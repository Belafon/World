package Game.Items;

import Game.Items.TypeItem.TypeItem;
import Game.Maps.Place.Place;
import Game.Maps.Place.UnboundedPlace;
import Game.ObjectsMemory.Visible;
import Game.World;
import Game.Calendar.Events.Event;
import Game.Calendar.Events.EventItemChange;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;

public class Item implements Visible, BehavioursPossibleIngredients{
    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile EventItemChange eventItemChange;
    public UnboundedPlace location; // TODO change location, when is moving
    private int weight;

    
    public Item(World game, TypeItem type) {
        this.id = game.ItemId();
        this.type = type;
        this.weight = type.regularWeight;
    }
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public synchronized int changeStats(Event event, World game) {
        return 0;
    }
    
    @Override
    public UnboundedPlace getLocation() {
        return location;
    }
}