package Game.ObjectsMemory.CreaturesMemory;

import java.util.ArrayList;
import java.util.List;
import Game.ObjectsMemory.ObjectsMemoryCell;

public class ActualConditionMemory {
    public final List<ObjectsMemoryCell<Integer>> hunger = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> fatigueMax = new ArrayList<>(); // = bariera pro odpo�inek, v��e zv��it fatigue nem��e, jedin� sp�nkem , about 
	public final List<ObjectsMemoryCell<Integer>> heat = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> bleeding = new ArrayList<>(); 
}
