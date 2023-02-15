package Game.Items.Types;

import Game.World;
import Game.Items.Item;
import Game.Items.TypeItem.TypeItem;
import Game.Maps.Place.Place;

public class SpaceItem extends Item {
    private Place space;

    public SpaceItem(World game, TypeItem type, Place space) {
        super(game, type);
        this.space = space;
    }
    
    public Place getSpace() {
        return space;
    }
    public void setSpace(Place space) {
        this.space = space;
    }

    @Override
    public int getWeight() {
        return super.getWeight() + space.items.stream().mapToInt((x)->x.getWeight()).sum() + space.creatures.stream().mapToInt((x)->x.getWeight()).sum();
    }
    
}
