package com.belafon.Game.ObjectsMemory.ItemsMemory;

import java.util.ArrayList;
import java.util.List;

import com.belafon.Game.Calendar.Events.EventItemChange;
import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;

public class FoodItemsMemory {
    public final List<ObjectsMemoryCell<Integer>> freshness = new ArrayList<>();
    public final List<ObjectsMemoryCell<Integer>> warm = new ArrayList<>();
    public final List<ObjectsMemoryCell<EventItemChange>> changeFreshness = new ArrayList<>();
    public final List<ObjectsMemoryCell<EventItemChange>> changeWarm = new ArrayList<>();
}
