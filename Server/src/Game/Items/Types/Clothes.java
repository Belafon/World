package Game.Items.Types;

import Game.World;
import Game.Items.Item;
import Game.Items.TypeItem.ClothesTypeItem;

public class Clothes extends Item{
    public final int dirty;
    public Clothes(World game, ClothesTypeItem type, int dirty) {
        super(game, type);
        this.dirty = dirty;
    }
    public ClothesTypeItem getType(){
        return (ClothesTypeItem)type;
    }
}
