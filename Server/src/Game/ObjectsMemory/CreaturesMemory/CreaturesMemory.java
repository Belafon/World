package Game.ObjectsMemory.CreaturesMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import Game.Maps.Place.Place;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.Visible;

public class CreaturesMemory {
    private final List<ObjectsMemoryCell<String>> name = new ArrayList<>();
    private final ReentrantLock mutexName = new ReentrantLock();
    private final List<ObjectsMemoryCell<Place>> position = new ArrayList<>();
    private final ReentrantLock mutexPosition = new ReentrantLock();
    private final List<ObjectsMemoryCell<String>> appearance = new ArrayList<>();
    private final ReentrantLock mutexAppearance = new ReentrantLock();
    private final List<ObjectsMemoryCell<Visible>> visibleObjectSpotted = new ArrayList<>();
    private final ReentrantLock mutexVisibleObjectSpotted = new ReentrantLock();
    private final List<ObjectsMemoryCell<Integer>> weight = new ArrayList<>();
    private final ReentrantLock mutexWeight = new ReentrantLock();
    public ObjectsMemoryCell<String> getName(int i) {
        mutexName.lock();
        try {
            return name.get(i);
        } finally {
            mutexName.unlock();
        }
    }

    public ObjectsMemoryCell<Place> getPosition(int i) {
        mutexPosition.lock();
        try {
            return position.get(i);
        } finally {
            mutexPosition.unlock();
        }
    }

    public ObjectsMemoryCell<String> getAppearance(int i) {
        mutexAppearance.lock();
        try {
            return appearance.get(i);
        } finally {
            mutexAppearance.unlock();
        }
    }

    public ObjectsMemoryCell<Visible> getVisibleObjectSpotted(int i) {
        mutexVisibleObjectSpotted.lock();
        try {
            return visibleObjectSpotted.get(i);
        } finally {
            mutexVisibleObjectSpotted.unlock();
        }
    }

    public ObjectsMemoryCell<Integer> getWeight(int i) {
        mutexWeight.lock();
        try {
            return weight.get(i);
        } finally {
            mutexWeight.unlock();
        }
    }

    public void addName(ObjectsMemoryCell<String> value) {
        mutexName.lock();
        try {
            name.add(value);
        } finally {
            mutexName.unlock();
        }
    }

    public void addPosition(ObjectsMemoryCell<Place> value) {
        mutexPosition.lock();
        try {
            position.add(value);
        } finally {
            mutexPosition.unlock();
        }
    }

    public void addAppearance(ObjectsMemoryCell<String> value) {
        mutexAppearance.lock();
        try {
            appearance.add(value);
        } finally {
            mutexAppearance.unlock();
        }
    }

    public void addVisibleObjectSpotted(ObjectsMemoryCell<Visible> value) {
        mutexVisibleObjectSpotted.lock();
        try {
            visibleObjectSpotted.add(value);
        } finally {
            mutexVisibleObjectSpotted.unlock();
        }
    }

    public void addWeight(ObjectsMemoryCell<Integer> value) {
        mutexWeight.lock();
        try {
            weight.add(value);
        } finally {
            mutexWeight.unlock();
        }
    }

}
