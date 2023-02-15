package Game.Creatures.Inventory;

import java.util.ArrayList;

import Console.ConsolePrint;
import Game.Items.Types.*;
import Game.Maps.Place.Place;
import Game.Creatures.Creature;
import Game.Items.Item;
import Game.Items.ListOfAllItemTypes;

public class Inventory {
    public volatile int totalWeight;
	public volatile int currentWeight;

    private SpaceItem backSpace;

	public final Gear gear;
    public final Creature creature;

    public Inventory(Gear gear, Creature creature) {
        this.gear = gear;
        this.creature = creature;
        backSpace = new SpaceItem(creature.game, ListOfAllItemTypes.spaceItems.get(ListOfAllItemTypes.NamesOfSpaceItemTypes.Bag), new Place());
    }

    public volatile Tool leftHand;
	public volatile Tool workHand;

    public synchronized void addItem(Item item) {
        if (backSpace == null) {
            // TODO call unsupported operation
            ConsolePrint.error("Try to add item to inventory without bag");
        }

        // lets send info about it
        creature.writer.inventory.setAddItem(item);
    }

	public synchronized void removeItem(Item item) {
		if(item instanceof Food food) foodInBag.remove(food);
		else if(item instanceof Clothes clothes) clothesInBag.remove(clothes);
		else if(item instanceof Tool tool) toolsInBag.remove(tool);
		else if(item instanceof QuestItem questItem) questItemsInBag.remove(questItem);
		else basicItemsInBag.remove(item);

		// lets send info about it
		creature.writer.inventory.setRemoveItem(item);
	}
}
