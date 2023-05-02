package com.belafon.world.visibles.creatures.inventory;

import com.belafon.console.ConsolePrint;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.items.Item;
import com.belafon.world.visibles.items.ListOfAllItemTypes;
import com.belafon.world.visibles.items.typeItem.ToolTypeItem;
import com.belafon.world.visibles.items.typeItem.tools.ToolsUtilization;
import com.belafon.world.visibles.items.types.*;
import com.belafon.world.visibles.items.types.SpaceItem.BuilderSpaceItem;

public class Inventory {
    public volatile int totalWeight;
    public volatile int currentWeight;

    private final SpaceItem backSpace;

    public final Gear gear;
    public final Creature creature;

    public Inventory(Gear gear, Creature creature, UnboundedPlace position) {
        this.gear = gear;
        this.creature = creature;
        backSpace = new BuilderSpaceItem(creature.game,
                ListOfAllItemTypes.spaceItems.get(ListOfAllItemTypes.NamesOfSpaceItemTypes.back_space),
                position)
                .build(creature.game);
    }

    public volatile Tool leftHand;
    public volatile Tool workHand;

    // item has moved
    public synchronized void addItem(Item item) {
        if (backSpace == null) {
            // TODO call unsupported operation
            ConsolePrint.error("Try to add item to inventory without bag");
        }
        backSpace.getSpace().addItem(item);
        // creature.behaviourCondition.addBehavioursPossibleRequirement(item.type,
        // item); -> wrong
        // because that item could be reachable before that

        item.location = backSpace.getLocation();

        if (item instanceof Tool tool) {
            if (tool.type instanceof ToolTypeItem toolType) {
                for (ToolsUtilization toolsUtilization : toolType.toolsUtilizations) {
                    creature.behaviourCondition.addBehavioursPossibleIngredient(toolsUtilization, item);
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
