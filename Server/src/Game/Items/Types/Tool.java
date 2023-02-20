package Game.Items.Types;

import Game.World;
import Game.Items.Item;
import Game.Items.TypeItem.TypeItem;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.ItemsMemory.ToolItemsMemory;

public class Tool extends Item {
    public final ToolItemsMemory memory = new ToolItemsMemory();    
    private volatile int quality;

    public Tool(World game, TypeItem type, int quality) {
        super(game, type);
        this.quality = quality;
    }
    
    public int getQuality() {
        return quality;
    }
    public void setQuality(int quality) {
        memory.quality.add(new ObjectsMemoryCell<Integer>(location.game.time.getTime(), this.quality));
        this.quality = quality;
    }



}
