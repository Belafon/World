package com.belafon.Game.Maps.Place;

import com.belafon.Game.World;
import com.belafon.Game.Items.Item;

/**
 * It is unbounded place, which is referenced with some item,
 * usually with Item of SpaceTypeItem type.
 * It can be used for representing a place like the inside 
 * of bag, or space of wardrowbe, or back of creature.
 */
public class ItemPlace extends UnboundedPlace {
    private final Item item;

    public ItemPlace(TypeOfPlace typeOfPlace, World game, Item item) {
        super(typeOfPlace, game);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public int getTemperature() {
        return item.getLocation().getTemperature();
    }

}
