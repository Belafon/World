package Game.Items;

import Game.Items.TypeItem.TypeItem;
import java.util.ArrayList;

import Game.Game;
import Game.Calendar.Events.Event;
import Game.Calendar.Events.EventItemChange;
import Game.Creatures.Creature;

public class Item {
    public final int id;
    public final TypeItem type;
    public volatile Creature owner;
    public volatile ArrayList<Creature> listOfCreaturesWhichKnowAboutLocation = new ArrayList<Creature>();
    public volatile EventItemChange eventItemChange;
    public Item(Game game, TypeItem type) {
        this.id = game.ItemId();
        this.type = type;
    }
    public synchronized int changeStats(Event event, Game game) {
        return 0;
    }
    public synchronized void cleareListOfCreatureWhoKnowAboutLocation(){
        listOfCreaturesWhichKnowAboutLocation = new ArrayList<Creature>();
    }
}