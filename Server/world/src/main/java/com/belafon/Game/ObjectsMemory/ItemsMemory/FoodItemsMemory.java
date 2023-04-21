package com.belafon.Game.ObjectsMemory.ItemsMemory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.belafon.Game.Calendar.Events.EventItemChange;
import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;

public class FoodItemsMemory {
    public final List<ObjectsMemoryCell<Integer>> freshness = Collections.synchronizedList(new ArrayList<>());
    public final List<ObjectsMemoryCell<Integer>> warm = Collections.synchronizedList(new ArrayList<>());
    public final List<ObjectsMemoryCell<EventItemChange>> changeFreshness = Collections.synchronizedList(new ArrayList<>());
    public final List<ObjectsMemoryCell<EventItemChange>> changeWarm = Collections.synchronizedList(new ArrayList<>());
}
