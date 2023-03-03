package Game.ObjectsMemory.CreaturesMemory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Maps.Place.Place;
import Game.Maps.Place.UnboundedPlace;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.Visible;

public class CreaturesMemory {
    private final List<ObjectsMemoryCell<String>> name = new ArrayList<>();
    public final ReentrantLock mutexName = new ReentrantLock();
    private final List<ObjectsMemoryCell<Place>> position = new ArrayList<>();
    public final ReentrantLock mutexPosition = new ReentrantLock();
    private final List<ObjectsMemoryCell<String>> appearance = new ArrayList<>();
    public final ReentrantLock mutexAppearance = new ReentrantLock();

    private final Hashtable<Place, List<ObjectsMemoryCell<Visible>>> lastVisiblesPositionWhenVisionLost = new Hashtable<>();
    private final ReentrantLock mutexlastVisiblesPositionWhenVisionLost = new ReentrantLock();

    private final List<ObjectsMemoryCell<Visible>> visibleObjectSpotted = new ArrayList<>();
    public final ReentrantLock mutexVisibleObjectSpotted = new ReentrantLock();

    private final List<ObjectsMemoryCell<Integer>> weight = new ArrayList<>();
    public final ReentrantLock mutexWeight = new ReentrantLock();

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

    public ObjectsMemoryCell<Place> getLatestPosition() {
        mutexPosition.lock();
        try {
            if (position.size() > 0)
                return position.get(position.size() - 1);
            else
                return null;
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

    public List<ObjectsMemoryCell<Visible>> getVisibleObjectSpotted(Place place) {
        mutexlastVisiblesPositionWhenVisionLost.lock();
        try {
            return lastVisiblesPositionWhenVisionLost.get(place);
        } finally {
            mutexlastVisiblesPositionWhenVisionLost.unlock();
        }
    }

    public ObjectsMemoryCell<Visible> getVisibleObjectLostFromSight(int i) {
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

    public void addVisibleObjectSpotted(ObjectsMemoryCell<Visible> value, UnboundedPlace place, Creature creature) {
        mutexlastVisiblesPositionWhenVisionLost.lock();
        try {
            lastVisiblesPositionWhenVisionLost.get(place).add(value);
            for (BehavioursPossibleRequirement requirement : value.object().getBehavioursPossibleRequirementType()) {
                creature.addBehavioursPossibleRequirement(requirement, value.object());
            }
        } finally {
            mutexlastVisiblesPositionWhenVisionLost.unlock();
        }
    }

    public int getVisibleObjectSpottedSize() {
        mutexlastVisiblesPositionWhenVisionLost.lock();
        try {
            return lastVisiblesPositionWhenVisionLost.size();
        } finally {
            mutexlastVisiblesPositionWhenVisionLost.unlock();
        }
    }

    public void addVisibleObjectLostFromSight(ObjectsMemoryCell<Visible> value) {
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
