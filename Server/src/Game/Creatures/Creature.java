package Game.Creatures;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import Game.World;
import Game.Creatures.Behaviour.Behaviour;
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
public abstract class Creature implements Visible {
    public volatile String name;
    protected volatile Place position;
    public final String appearence;
	public final AbilityCondition abilityCondition;
	public final ActualCondition actualCondition;
	public volatile Behaviour currentBehaviour = null; // idle condition ;
	public volatile Inventory inventory;
	public volatile World game;
	public volatile int id;
    public final SendMessage writer;
    private volatile int weight;
    private Set<Knowledge> knowledge = new ConcurrentSkipListSet<>();
    private final ConcurrentHashMap<BehavioursPossibleRequirement, ArrayList<BehavioursPossibleIngredients>> behavioursProperties = new ConcurrentHashMap<>(); 
    
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

    public void addBehavioursPossibleRequirement(BehavioursPossibleRequirement behavioursPossibleRequirement, BehavioursPossibleIngredients behavioursPossibleIngredients) {
        if (behavioursProperties.containsKey(behavioursPossibleRequirement)) {
            behavioursProperties.get(behavioursPossibleRequirement).add(behavioursPossibleIngredients);
        } else {
            ArrayList<BehavioursPossibleIngredients> list = new ArrayList<>();
            list.add(behavioursPossibleIngredients);
            behavioursProperties.put(behavioursPossibleRequirement, list);
        }
    }
    
    public Set<Knowledge> getKnowledge() {
        return knowledge;
    }
    public void addKnowledge(Knowledge knowledge) {
        this.knowledge.add(knowledge);
        addBehavioursPossibleRequirement(knowledge.type, knowledge);
    }

}

