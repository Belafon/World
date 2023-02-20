package Game.Items.Types;

import Game.World;
import Game.Items.Item;
import Game.Items.TypeItem.TypeItem;
import Game.Maps.Place.UnboundedPlace;

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
