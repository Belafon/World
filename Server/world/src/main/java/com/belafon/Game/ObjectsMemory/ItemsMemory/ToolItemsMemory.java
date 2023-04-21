package com.belafon.Game.ObjectsMemory.ItemsMemory;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;


public class ToolItemsMemory {
    public final List<ObjectsMemoryCell<Integer>> quality = Collections.synchronizedList(new ArrayList<>());
}
