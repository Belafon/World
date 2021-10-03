package Game.Creatures.Inventory;

import java.util.ArrayList;
import Game.Items.Types.*;
import Game.Creatures.Creature;
import Game.Creatures.Player;
import Game.Items.Item;

public class Inventory {
    public volatile int totalWeight;
	public volatile int currentWeight;

    public ArrayList<Food> foodInBag;
	public ArrayList<Clothes> clothesInBag;
	public ArrayList<Tool> toolsInBag;
	public ArrayList<QuestItem> questItemsInBag;
	public ArrayList<Item> basicItemsInBag;
	public final Gear gear;
    public final Creature creature;

    public Inventory(Gear gear, Creature creature) {
        this.gear = gear;
        this.creature = creature;
        foodInBag = new ArrayList<Food>();
		clothesInBag = new ArrayList<Clothes>();
		toolsInBag = new ArrayList<Tool>();
		basicItemsInBag = new ArrayList<Item>();
		questItemsInBag = new ArrayList<QuestItem>();
    }

    public volatile Tool leftHand;
	public volatile Tool workHand;

    public synchronized void addItem(Item item) {
		if(item instanceof Food) foodInBag.add((Food)item);
		else if(item instanceof Clothes) clothesInBag.add((Clothes)item);
		else if(item instanceof Tool) toolsInBag.add((Tool)item);
		else if(item instanceof QuestItem) questItemsInBag.add((QuestItem)item);
		else basicItemsInBag.add(item);

		// lets send info about it
		if(creature != null && creature instanceof Player)
            ((Player)creature).client.writer.setAddItem(item);
	}

	public synchronized void removeItem(Item item) {
		if(item instanceof Food) foodInBag.remove((Food)item);
		else if(item instanceof Clothes) clothesInBag.remove((Clothes)item);
		else if(item instanceof Tool) toolsInBag.remove((Tool)item);
		else if(item instanceof QuestItem) questItemsInBag.remove((QuestItem)item);
		else basicItemsInBag.remove(item);

		// lets send info about it
		if(creature != null && creature instanceof Player)
            ((Player)creature).client.writer.setRemoveItem(item);
	}
}
