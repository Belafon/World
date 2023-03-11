package com.belafon.Game.Items.Types;

import com.belafon.Game.World;
import com.belafon.Game.Items.Item;
import com.belafon.Game.Items.TypeItem.TypeItem;
import com.belafon.Game.Maps.Place.UnboundedPlace;

public class SpaceItem extends Item {
    private UnboundedPlace space;

    public SpaceItem(World game, TypeItem type, UnboundedPlace space) {
        super(game, type);
        this.space = space;
    }
    
    public UnboundedPlace getSpace() {
        return space;
    }
    public void setSpace(UnboundedPlace space) {
        this.space = space;
    }

    @Override
    public int getWeight() {
        return super.getWeight() + space.items.stream().mapToInt((x)->x.getWeight()).sum() + space.creatures.stream().mapToInt((x)->x.getWeight()).sum();
    }
    
}
