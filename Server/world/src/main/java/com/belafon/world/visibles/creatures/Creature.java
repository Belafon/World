package com.belafon.world.visibles.creatures;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantLock;

import com.belafon.server.sendMessage.MessageSender;
import com.belafon.world.World;
import com.belafon.world.maps.place.Place;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.objectsMemory.ObjectsMemoryCell;
import com.belafon.world.objectsMemory.Visible;
import com.belafon.world.objectsMemory.creaturesMemory.CreaturesMemory;
import com.belafon.world.visibles.creatures.behaviour.Behaviour;
import com.belafon.world.visibles.creatures.behaviour.BehaviourCondition;
import com.belafon.world.visibles.creatures.behaviour.PlayersLookAround;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.visibles.creatures.condition.AbilityCondition;
import com.belafon.world.visibles.creatures.condition.ActualCondition;
import com.belafon.world.visibles.creatures.condition.knowledge.Knowledge;
import com.belafon.world.visibles.creatures.inventory.Inventory;

public abstract class Creature extends Visible {
    // this is requirement that says any creature is required for some behaviour
    // exection
    public static final BehavioursPossibleRequirement REQUIREMENT = new BehavioursPossibleRequirement("Creature is visible.") {
    };
    public volatile String name;
    protected volatile UnboundedPlace position;
    public final String appearence;
    public final AbilityCondition abilityCondition;
    public final ActualCondition actualCondition;
    public final BehaviourCondition behaviourCondition;
    public final InfluencingActivities influencingActivities;

    /**
     * null means idle, each creature can do just one behaviour,
     * which duration is not 0, in time.
     */
    public Behaviour currentBehaviour = null; // idle
    public Inventory inventory;
    public final World game;
    public final int id;
    public final MessageSender writer;
    private volatile int weight;
    private final Set<Knowledge> knowledge = new ConcurrentSkipListSet<>();

    private final Set<Visible> currentlyVisibleObjects = ConcurrentHashMap.newKeySet();
    private final ReentrantLock mutexCurrentlyVisibleObjects = new ReentrantLock();

    public final CreaturesMemory memory = new CreaturesMemory();

    public PlayersLookAround surroundingPlaces;

    public Creature(World game, String name, UnboundedPlace position,
            String appearence, MessageSender sendMessage,
            int weight) {
        this.id = game.visibleIds.getCreatureId();
        this.game = game;
        this.writer = sendMessage;
        this.position = position;
        this.appearence = appearence;
        this.name = name;
        this.weight = weight;
        influencingActivities = new InfluencingActivities(writer);
        setInventory(position);
        abilityCondition = new AbilityCondition(this, 100, 100, 100, 100, 100, 100, 100);
        behaviourCondition = new BehaviourCondition(this);
        actualCondition = new ActualCondition(this);
        surroundingPlaces = PlayersLookAround.look(position);
    }

    public void setAbilityCondition(int strength, int agility, int speed_of_walk, int speed_of_run, int hearing,
            int observation, int vision) {
        // strength, agility, speed_of_walk, speed_of_run, hearing, observation, vision
        abilityCondition.setStrength(strength);
        abilityCondition.setAgility(agility);
        abilityCondition.setSpeedOfWalk(speed_of_walk);
        abilityCondition.setSpeedOfRun(speed_of_run);
        abilityCondition.setObservation(observation);
        abilityCondition.setHearing(hearing);
        abilityCondition.setVision(vision);
    }

    /**
     * Initializes invertory of some creature.
     * 
     * @param position
     */
    protected abstract void setInventory(UnboundedPlace position);

    /**
     * executes creatures behaviour
     * // TODO check behaviours possible requirements and ingredients
     * 
     * @param behaviour
     */
    public void setBehaviour(Behaviour behaviour) {
        currentBehaviour = behaviour;
        behaviour.execute();
        getWatchers((watchers) -> {
            for (Creature creature : watchers)
                creature.influencingActivities.otherCreaturesBehaviourChanged(creature);
        });
    }

    /**
     * Changed creatures position.
     * 
     * @param position
     */
    public void setLocation(Place position) {
        PlayersLookAround lastLook = this.surroundingPlaces;
        memory.addPosition(new ObjectsMemoryCell<Place>(game.time.getTime(), position));
        this.position = position;
        this.surroundingPlaces = PlayersLookAround.look(position);

        for (int row = 0; row < lastLook.visiblePlaces.length; row++) {
            for (int col = 0; col < lastLook.visiblePlaces[row].length; col++) {
                if (lastLook.visiblePlaces[row][col] == null)
                    continue;

    //            if (!surroundingPlaces.isPlaceVisible(lastLook.visiblePlaces[row][col])) {
                    // remove all visibles from the place
                    for (Visible visible : currentlyVisibleObjects)
                        removeVisibleObject(visible);
    /*             } else {
                    // find all visible items that are not visible anymore and remove them
                    for (Visible visible : currentlyVisibleObjects)
                        if (!shouldBeVisibleStillVisible(visible))
                            removeVisibleObject(visible);
                } */
            }
        }
        writer.surrounding.setPosition(surroundingPlaces);

        // all players watching that have to get notice that
        // TODO chack if the watcher can still see the creature
        getWatchers((watchers) -> {
            for (Creature creature : watchers)
                creature.influencingActivities.otherCreaturesPositionChanged(creature);
        });
        memory.getVisibleObjectLostFromSight((lostVisibles) -> {
            if (lostVisibles.containsKey(position)) {
                for (ObjectsMemoryCell<Visible> lostVisible : lostVisibles.get(position)) {
                    if (lostVisible.object().getLocation() == position)
                        addVisibleObject(lostVisible.object());
                }
                lostVisibles.get(position).clear();
            }
        });
    }

    private boolean shouldBeVisibleStillVisible(Visible visible) {
        UnboundedPlace position = visible.getLocation();
        // TODO update, use created sorted list of visibles in concrete unboundedPlace
        if (visible.getVisibility() < 50)
            return true;
        return false;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        memory.addWeight(new ObjectsMemoryCell<Integer>(game.time.getTime(), weight));
        this.weight = weight;
    }

    @Override
    public UnboundedPlace getLocation() {
        return position;
    }

    public Set<Knowledge> getKnowledge() {
        return knowledge;
    }

    public void addKnowledge(Knowledge knowledge) {
        this.knowledge.add(knowledge);
        behaviourCondition.addBehavioursPossibleIngredient(knowledge.type, knowledge);
        writer.condition.addKnowledge(knowledge);
    }

    public void removeKnowledge(Knowledge knowledge) {
        this.knowledge.remove(knowledge);
        behaviourCondition.removeBehavioursPossibleRequirement(knowledge.type, knowledge);
        writer.condition.addKnowledge(knowledge);
    }

    /**
     * Adds new visible object to creatures vision.
     * This information is also saved in
     * {@link Game.ObjectsMemory.CreaturesMemory.CreaturesMemory.visibleObjectSpotted}
     * and also in
     * {@link Game.ObjectsMemory.CreaturesMemory.CreaturesMemory.lastVisiblesPositionWhenVisionLost}
     * Also, the set of currently possible behaviours is updated
     * 
     * Also, the viewed object memorizes the information in
     * {@link Game.ObjectsMemory.Visible.watchers}
     * 
     * @param visible
     */
    public void addVisibleObject(Visible visible) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            currentlyVisibleObjects.add(visible);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }

        memory.addVisibleObjectSpotted(new ObjectsMemoryCell<Visible>(game.time.getTime(), visible));

        visible.addWatcher(this);

        this.writer.surrounding.addVisibleInSight(visible, this);

        for (BehavioursPossibleRequirement requirement : visible.getBehavioursPossibleRequirementType(this)) {
            behaviourCondition.addBehavioursPossibleIngredient(requirement, visible);
        }
    }

    @FunctionalInterface
    public interface ActionGetCurremtlyObjectSpotted {
        void doJob(Set<Visible> visibleObjectSpotted);
    }

    /**
     * This function is for manipulating with visibles list.
     * This aim to require stay safe even with concurent approach.
     */
    public void getCurrentlyVisibleObjectSpotted(ActionGetCurremtlyObjectSpotted visibles) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            visibles.doJob(currentlyVisibleObjects);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }
    }

    /**
     * This method removes some visible from creatures sight.
     * The creature cannot see the visible anymore.
     */
    public void removeVisibleObject(Visible visible) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            currentlyVisibleObjects.remove(visible);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }

        memory.addVisibleObjectLostFromSight(new ObjectsMemoryCell<Visible>(game.time.getTime(), visible),
                visible.getLocation(), this);

        visible.removeWatcher(this);

        this.writer.surrounding.removeVisibleFromSight(visible);

        for (BehavioursPossibleRequirement requirement : visible.getBehavioursPossibleRequirementType(this)) {
            behaviourCondition.addBehavioursPossibleIngredient(requirement, visible);
        }
    }

    /**
     * Checks if the creature sees the given visible
     * 
     * @param value
     * @return
     */
    public boolean seesVisibleObject(Visible value) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            return currentlyVisibleObjects.contains(value);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }
    }

    @Override
    public List<BehavioursPossibleRequirement> getBehavioursPossibleRequirementType(Creature creature) {
        return Arrays.asList(REQUIREMENT);
    }

    @Override
    public int getVisibility() {
        // TODO needs update
        return abilityCondition.getLoudness();
    }
}
