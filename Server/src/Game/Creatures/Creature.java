package Game.Creatures;

import Game.Game;
import Game.Creatures.Behaviour.Behaviour;
import Game.Creatures.Condition.AbilityCondition;
import Game.Maps.Place.Place;
import Server.SendMessage.SendMessage;
import Game.Creatures.Condition.ActualCondition;
import Game.Creatures.Inventory.Inventory;
public abstract class Creature {
    public volatile String name;
    protected volatile Place position;
    public final String appearence;
	public final AbilityCondition abilityCondition;
	public final ActualCondition actualCondition;
	public volatile Behaviour currentBehaviour = null; // idle condition ;
	public volatile Inventory inventory;
	public volatile Game game;
	public volatile int id;
    public final SendMessage writer;

    public Creature(Game game, String name, Place position, int id, String appearence, SendMessage sendMessage){
		this.id = id;
    	this.game = game;
        this.appearence = appearence;
    	actualCondition = new ActualCondition(this);
        abilityCondition = new AbilityCondition(this, 100, 100, 100, 100, 100, 100, 100);
        this.position = position;
        this.name = name;
        this.writer = sendMessage;
    }
    public void setAbilityCondition(int strength, int agility, int speed_of_walk, int speed_of_run, int hearing, int observation, int vision) {
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
    public void setBehaviour(Behaviour behaviour){
        currentBehaviour = behaviour;
        behaviour.execute();
    }
    public Place getPosition() {
        return position;
    }
    public void setPosition(Place position) {
        this.position = position;
        writer.surrounding.setPosition(position);
    }
}

