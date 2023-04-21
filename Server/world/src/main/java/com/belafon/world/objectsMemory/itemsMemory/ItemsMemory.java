package com.belafon.world.objectsMemory.itemsMemory;

import java.util.ArrayList;
import java.util.List;

import com.belafon.world.creatures.Creature;
import com.belafon.world.objectsMemory.ObjectsMemoryCell;

import java.util.Collections;

public class ItemsMemory {
    public final List<ObjectsMemoryCell<Creature>>  owner = Collections.synchronizedList(new ArrayList<>());
}
