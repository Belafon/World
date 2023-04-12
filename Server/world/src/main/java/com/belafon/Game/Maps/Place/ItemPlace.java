package com.belafon.Game.Maps.Place;

import com.belafon.Game.World;
import com.belafon.Game.Items.Item;

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
