package com.belafon.Game.ObjectsMemory.ItemsMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;

public class ClothesItemMemory {
    public final List<ObjectsMemoryCell<Integer>> dirty = Collections.synchronizedList(new ArrayList<>());
}
