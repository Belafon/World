package com.belafon.Game.Creatures;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantLock;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Behaviour.Behaviour;
import com.belafon.Game.Creatures.Behaviour.BehaviourCondition;
import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import com.belafon.Game.Creatures.Condition.AbilityCondition;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.ObjectsMemory.ObjectsMemoryCell;
import com.belafon.Game.ObjectsMemory.Visible;
import com.belafon.Game.ObjectsMemory.CreaturesMemory.CreaturesMemory;
import com.belafon.Server.SendMessage.MessageSender;
import com.belafon.Game.Creatures.Condition.ActualCondition;
import com.belafon.Game.Creatures.Condition.Knowledge.Knowledge;
import com.belafon.Game.Creatures.Inventory.Inventory;

public abstract class Creature extends Visible {
    public static final BehavioursPossibleRequirement REQUIREMENT = new BehavioursPossibleRequirement() {
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

    private final Set<Visible> currentlyVisibleObjects = new HashSet<>();
    private final ReentrantLock mutexCurrentlyVisibleObjects = new ReentrantLock();

    public final CreaturesMemory memory = new CreaturesMemory();

    public Creature(World game, String name, UnboundedPlace position,
            String appearence, MessageSender sendMessage,
            int weight) {
        this.id = game.visibleIds.getCreatureId();
        this.game = game;
        this.writer = sendMessage;
        influencingActivities = new InfluencingActivities(writer);
        setInventory();
        this.appearence = appearence;
        abilityCondition = new AbilityCondition(this, 100, 100, 100, 100, 100, 100, 100);
        behaviourCondition = new BehaviourCondition(this);
        actualCondition = new ActualCondition(this);
        this.position = position;
        this.name = name;
        this.weight = weight;
    }

    public void setAbilityCondition(int strength, int agility, int speed_of_walk, int speed_of_run, int hearing,
            int observation, int vision) {
        // strength, agility, speed_of_walk, speed_of_run, hearing, observation, vision
        this.abilityCondition.setStrength(strength);
        this.abilityCondition.setAgility(agility);
        abilityCondition.setSpeedOfWalk(speed_of_walk);
        abilityCondition.setSpeedOfRun(speed_of_run);
        abilityCondition.setObservation(observation);
        abilityCondition.setHearing(hearing);
        abilityCondition.setVision(vision);
    }

    protected abstract void setInventory();

    public void setBehaviour(Behaviour behaviour) {
        currentBehaviour = behaviour;
        behaviour.execute();
        getWatchers((watchers) -> {
            for (Creature creature : watchers)
                creature.influencingActivities.otherCreaturesBehaviourChanged(creature);
        });
    }

    public void setLocation(Place position) {
        memory.addPosition(new ObjectsMemoryCell<Place>(game.time.getTime(), position));
        this.position = position;
        writer.surrounding.setPosition(position);

        // all players watching that have to get notice that
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

        this.writer.surrounding.addVisibleInSight(visible);

        for (BehavioursPossibleRequirement requirement : visible.getBehavioursPossibleRequirementType()) {
            behaviourCondition.addBehavioursPossibleIngredient(requirement, visible);
        }
    }

    @FunctionalInterface
    public interface ActionGetCurremtlyObjectSpotted {
        void doJob(Set<Visible> visibleObjectSpotted);
    }

    public void getCurrentlyVisibleObjectSpotted(ActionGetCurremtlyObjectSpotted visibles) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            visibles.doJob(currentlyVisibleObjects);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }
    }

    public void removeVisibleObjects(Visible value) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            currentlyVisibleObjects.remove(value);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }

        memory.addVisibleObjectLostFromSight(new ObjectsMemoryCell<Visible>(game.time.getTime(), value),
                value.getLocation(), this);

        value.removeWatcher(this);

        this.writer.surrounding.removeVisibleFromSight(value);

        for (BehavioursPossibleRequirement requirement : value.getBehavioursPossibleRequirementType()) {
            behaviourCondition.addBehavioursPossibleIngredient(requirement, value);
        }
    }

    public boolean containsVisibleObject(Visible value) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            return currentlyVisibleObjects.contains(value);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }
    }

    @Override
    public BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType() {
        return new BehavioursPossibleRequirement[] { REQUIREMENT };
    }
}
