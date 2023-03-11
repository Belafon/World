package com.belafon.Game.ObjectsMemory.ItemsMemory;

import java.util.ArrayList;
import java.util.List;

import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;

public class ItemsMemory {
    public final List<ObjectsMemoryCell<Creature>>  owner = new ArrayList<>();
}
