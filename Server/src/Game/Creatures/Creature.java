package Game.Creatures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantLock;

import Game.World;
import Game.Creatures.Behaviour.Behaviour;
import Game.Creatures.Behaviour.BehaviourType;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Creatures.Condition.AbilityCondition;
import Game.Maps.Place.Place;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.Visible;
import Game.ObjectsMemory.CreaturesMemory.CreaturesMemory;
import Server.SendMessage.SendMessage;
import Game.Creatures.Condition.ActualCondition;
import Game.Creatures.Condition.Knowledge.Knowledge;
import Game.Creatures.Inventory.Inventory;
public abstract class Creature extends BehavioursPossibleRequirement implements Visible {
    public volatile String name;
    protected volatile Place position;
    public final String appearence;
	public final AbilityCondition abilityCondition;
	public final ActualCondition actualCondition;
	public volatile Behaviour currentBehaviour = null; // idle 
	public volatile Inventory inventory;
	public volatile World game;
	public volatile int id;
    public final SendMessage writer;
    private volatile int weight;
    private Set<Knowledge> knowledge = new ConcurrentSkipListSet<>();
    private final Map<BehavioursPossibleRequirement, ArrayList<BehavioursPossibleIngredients>> behavioursProperties = new ConcurrentHashMap<>();
    
    private final Set<Visible> currentlyVisibleObjects = new HashSet<>();
    private final ReentrantLock mutexCurrentlyVisibleObjects = new ReentrantLock();
    private final Set<BehaviourType> feasibleBehaviours = new ConcurrentSkipListSet<>();

    
    public final CreaturesMemory memory = new CreaturesMemory();
    public Creature(World game, String name, Place position, int id, String appearence, SendMessage sendMessage, int weight){
		this.id = id;
    	this.game = game;
        this.appearence = appearence;
    	actualCondition = new ActualCondition(this);
        abilityCondition = new AbilityCondition(this, 100, 100, 100, 100, 100, 100, 100);
        this.position = position;
        this.name = name;
        this.writer = sendMessage;
        this.weight = weight;
    }
    public synchronized void setAbilityCondition(int strength, int agility, int speed_of_walk, int speed_of_run, int hearing, int observation, int vision) {
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

    public synchronized void setBehaviour(Behaviour behaviour) {
        currentBehaviour = behaviour;
        behaviour.execute();
    }
    
    public synchronized void setLocation(Place position) {
        memory.addPosition(new ObjectsMemoryCell<Place>(game.time.getTime(), position));
        this.position = position;
        writer.surrounding.setPosition(position);

        // all players watching that have to get notice that
    }
    
    public int getWeight() {
        return weight;
    }

    public synchronized void setWeight(int weight) {
        memory.addWeight(new ObjectsMemoryCell<Integer>(game.time.getTime(), weight));
        this.weight = weight;
    }

    
    @Override
    public Place getLocation() {
        return position;
    }

    /**
     * checks wether the creature can do some other behaviour.
     * 
     * This is called when behavioursProperties keys updated,
     * or when new Visible spotted, or lost from sight 
     */
    public void addBehavioursPossibleRequirement(BehavioursPossibleRequirement behavioursPossibleRequirement,
            BehavioursPossibleIngredients behavioursPossibleIngredients) {

        if (behavioursProperties.containsKey(behavioursPossibleRequirement)) {
            behavioursProperties.get(behavioursPossibleRequirement).add(behavioursPossibleIngredients);
        } else {
            ArrayList<BehavioursPossibleIngredients> list = new ArrayList<>();
            list.add(behavioursPossibleIngredients);
            behavioursProperties.put(behavioursPossibleRequirement, list);
        }
        checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdding(behavioursPossibleRequirement);
    }
    
    public void removeBehavioursPossibleRequirement(BehavioursPossibleRequirement behavioursPossibleRequirement,
            BehavioursPossibleIngredients behavioursPossibleIngredients) {

        if (behavioursProperties.containsKey(behavioursPossibleRequirement)) {
            behavioursProperties.get(behavioursPossibleRequirement).add(behavioursPossibleIngredients);
        } else {
            ArrayList<BehavioursPossibleIngredients> list = new ArrayList<>();
            list.add(behavioursPossibleIngredients);
            behavioursProperties.put(behavioursPossibleRequirement, list);
        }
        checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdding(behavioursPossibleRequirement);
    }
    
    public Set<Knowledge> getKnowledge() {
        return knowledge;
    }

    public void addKnowledge(Knowledge knowledge) {
        this.knowledge.add(knowledge);
        addBehavioursPossibleRequirement(knowledge.type, knowledge);
        writer.condition.addKnowledge(knowledge);
    }


    /**
     * sends informations to creature if it is 
     * capable to do some new behaviour.  
     * @param newRequirement
     */
    private void checkPossibleBehavioursAfterNewBehavioursPossibleRequirementAdding(
            BehavioursPossibleRequirement newRequirement) {

        // lets get all behaviours, that needs requirement for execution
        Set<BehaviourType> behavioursTypes = newRequirement.behaviours;

        for (BehaviourType behaviourType : behavioursTypes) {
            boolean consumableSatisfies = behaviourType.consumableRequirements.entrySet().stream().filter((x) -> {
                if (behavioursProperties.containsKey(x.getKey())
                        && behavioursProperties.get(x.getKey()).size() >= x.getValue())
                    return false;
                return true;
            }).count() > 0 ? false : true;

            boolean unconsumableSatisfies = behaviourType.requirements.entrySet().stream().filter((x) -> {
                if (behavioursProperties.containsKey(x.getKey())
                        && behavioursProperties.get(x.getKey()).size() >= x.getValue())
                    return false;
                return true;
            }).count() > 0 ? false : true;

            if (consumableSatisfies && unconsumableSatisfies) {
                addNewFeasibleBehaviour(behaviourType);
            }
        }

    }

    private void addNewFeasibleBehaviour(BehaviourType behaviourType) {
        feasibleBehaviours.add(behaviourType);
        writer.behavioursMessages.newFeasibleBehaviour(behaviourType);
    }
       
    /**
     *  Adds new visible object to creatures vision.
     *  This information is also saved in {@link Game.ObjectsMemory.CreaturesMemory.CreaturesMemory.visibleObjectSpotted}
     *  and also in {@link Game.ObjectsMemory.CreaturesMemory.CreaturesMemory.lastVisiblesPositionWhenVisionLost} 
     *  Also, the set of currently possible behaviours is updated 
     * @param value
     */
    public void addVisibleObjects(Visible value) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            currentlyVisibleObjects.add(value);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
        }
        memory.addVisibleObjectSpotted(new ObjectsMemoryCell<Visible>(game.time.getTime(), value),
                value.getLocation(), this);

        for (BehavioursPossibleRequirement requirement : value.getBehavioursPossibleRequirementType()) {
            addBehavioursPossibleRequirement(requirement, value);
        }
    }

    public void removeVisibleObjects(Visible value) {
        mutexCurrentlyVisibleObjects.lock();
        try {
            currentlyVisibleObjects.remove(value);
        } finally {
            mutexCurrentlyVisibleObjects.unlock();
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
        return new BehavioursPossibleRequirement[]{this};
    }
}

