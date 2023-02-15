package Game.ObjectsMemory.CreaturesMemory;

import java.util.ArrayList;
import java.util.List;

import Game.Maps.Place.Place;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.Visible;

public class CreaturesMemory {
    public final List<ObjectsMemoryCell<String>> name = new ArrayList<>();
    public final List<ObjectsMemoryCell<Place>> position = new ArrayList<>();
    public final List<ObjectsMemoryCell<String>> appearence = new ArrayList<>();
    public final List<ObjectsMemoryCell<Visible>> visibleObjectSpoted = new ArrayList<>();
    public final List<ObjectsMemoryCell<Integer>> weight = new ArrayList<>();
}
