package Game.ObjectsMemory.CreaturesMemory;

import java.util.ArrayList;
import java.util.List;

import Game.ObjectsMemory.ObjectsMemoryCell;

public class AbilityConditionMemory {
    public final List<ObjectsMemoryCell<Integer>> health = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> strength = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> agility = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> speedOfWalk = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> speedOfRun = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> currentSpeed = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> hearing = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> observation = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> vision = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> loudness = new ArrayList<>();  
	public final List<ObjectsMemoryCell<Integer>> fatigue = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> attention = new ArrayList<>();
	public final List<ObjectsMemoryCell<Integer>> currentEnergyOutput = new ArrayList<>();
}
