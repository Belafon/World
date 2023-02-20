package Game.Items.Types;

import Game.World;
import Game.Items.Item;
import Game.Items.TypeItem.ClothesTypeItem;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.ItemsMemory.ClothesItemMemory;

public class Clothes extends Item{
    private int dirty;
    public final ClothesItemMemory memory = new ClothesItemMemory();

    public Clothes(World game, ClothesTypeItem type, int dirty) {
        super(game, type);
        this.dirty = dirty;
    }

    public ClothesTypeItem getType() {
        return (ClothesTypeItem) type;
    }

    public int getDirty() {
        return dirty;
    }

    public void setDirty(int dirty) {
        memory.dirty.add(new ObjectsMemoryCell<Integer>(location.game.time.getTime(), dirty));
        this.dirty = dirty;
    }
}
