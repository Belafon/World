package com.belafon.Game.Creatures.Inventory;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.Items.Types.*;
import com.belafon.Game.Maps.Place.ListOfAllTypesOfPlaces;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Items.Item;
import com.belafon.Game.Items.ListOfAllItemTypes;
import com.belafon.Game.Items.TypeItem.ToolTypeItem;
import com.belafon.Game.Items.TypeItem.Tools.ToolsUtilization;

public class Inventory {
    public volatile int totalWeight;
	public volatile int currentWeight;

    private SpaceItem backSpace;

	public final Gear gear;
    public final Creature creature;

    public Inventory(Gear gear, Creature creature) {
        this.gear = gear;
        this.creature = creature;
        backSpace = new SpaceItem(creature.game,
            ListOfAllItemTypes.spaceItems.get(ListOfAllItemTypes.NamesOfSpaceItemTypes.back_space),
            new UnboundedPlace(ListOfAllTypesOfPlaces.typeOfPlaces.get(ListOfAllTypesOfPlaces.NamesOfTypesOfPlaces.back_space), creature.game));
    }

    public volatile Tool leftHand;
	public volatile Tool workHand;

    // item has moved
    public synchronized void addItem(Item item) {
        if (backSpace == null) {
            // TODO call unsupported operation
            ConsolePrint.error("Try to add item to inventory without bag");
        }
        backSpace.getSpace().items.add(item);
        // creature.behaviourCondition.addBehavioursPossibleRequirement(item.type, item); -> wrong 
        // because that item could be reachable before that

        item.location = backSpace.getLocation();
        
        if (item instanceof Tool tool) {
            if(tool.type instanceof ToolTypeItem toolType){
                for (ToolsUtilization toolsUtilization : toolType.toolsUtilizations) {
                    creature.behaviourCondition.addBehavioursPossibleRequirement(toolsUtilization, item);
                }
            }
        }
        // lets send info about it
        creature.writer.inventory.setAddItem(item);
    }

	public synchronized void removeItem(Item item) {

		// lets send info about it
		creature.writer.inventory.setRemoveItem(item);
	}
}
