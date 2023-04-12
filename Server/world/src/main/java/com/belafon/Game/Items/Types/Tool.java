package com.belafon.Game.Items.Types;

import com.belafon.Game.World;
import com.belafon.Game.Items.Item;
import com.belafon.Game.Items.TypeItem.TypeItem;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;
import com.belafon.Game.ObjectsMemory.ItemsMemory.ToolItemsMemory;

public class Tool extends Item {
    public final ToolItemsMemory memory = new ToolItemsMemory();    
    private volatile int quality;

    public Tool(World game, TypeItem type, int quality, UnboundedPlace position) {
        super(game, type, position);
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
