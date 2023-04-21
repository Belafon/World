package com.belafon.world.items.types;

import com.belafon.world.World;
import com.belafon.world.items.Item;
import com.belafon.world.items.typeItem.TypeItem;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.objectsMemory.ObjectsMemoryCell;
import com.belafon.world.objectsMemory.itemsMemory.ToolItemsMemory;

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
