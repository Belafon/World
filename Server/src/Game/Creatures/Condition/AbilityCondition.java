package Game.Creatures.Condition;

import Game.Creatures.Creature;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.CreaturesMemory.AbilityConditionMemory;

public class AbilityCondition {
	private int health = 100;
	private int strength;
	private int agility;
	private int speedOfWalk;
	private int speedOfRun;
	private int currentSpeed;
	private int hearing;
	private int observation;
	private int vision;
	private int loudness;  
	private int fatigue = 100; // unava 0 - 100 (0 - unaven naprosto)
	private int attention;
	private volatile int currentEnergyOutput = 0;
    private Creature creature;
    public final AbilityConditionMemory memory = new AbilityConditionMemory();
	public AbilityCondition(Creature creature, int strength, int agility, int speed_of_walk
			, int speed_of_run, int hearing, int observation, int vision) {
		this.creature = creature;
		this.strength = strength;
		this.agility = agility;
		this.speedOfRun = speed_of_run;
		this.speedOfWalk = speed_of_walk;
		this.hearing = hearing;
		this.observation = observation;
		this.vision = vision;
		this.currentSpeed = this.speedOfWalk;
	}
	public int getCurrentEnergyOutput() {
		return currentEnergyOutput;
	}

    public void setCurrentEnergyOutput(int currentEnergyOutput) {
        memory.addCurrentEnergyOutput(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), currentEnergyOutput));
        this.currentEnergyOutput = currentEnergyOutput;
	}
	public int getStrength() {
		return strength;
	}

    public void setStrength(int strength) {
        memory.addStrength(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), strength));
        
        this.strength = strength;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
        memory.addAgility(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), agility));
        this.agility = agility;
	}
	public int getSpeedOfWalk() {
		return speedOfWalk;
	}
	public void setSpeedOfWalk(int speedOfWalk) {
        memory.addSpeedOfWalk(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), speedOfWalk));
        this.speedOfWalk = speedOfWalk;
	}
	public int getSpeedOfRun() {
		return speedOfRun;
	}
	public void setSpeedOfRun(int speedOfRun) {
        memory.addSpeedOfRun(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), speedOfRun));
        this.speedOfRun = speedOfRun;
	}
	public int getHearing() {
		return hearing;
	}
	public void setHearing(int hearing) {
        memory.addHearing(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), hearing));
        this.hearing = hearing;
	}
	public int getObservation() {
		return observation;
	}
	public void setObservation(int observation) {
        memory.addObservation(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), observation));
        this.observation = observation;
	}
	public int getVision() {
		return vision;
	}
	public void setVision(int vision) {
        memory.addVision(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), vision));
        this.vision = vision;
	}
	public int getCurrent_speed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(int currentSpeed) {
        memory.addCurrentSpeed(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), currentSpeed));
        this.currentSpeed = currentSpeed;
	}
	public int getLoudness() {
		return loudness;
	}
	public void setLoudness(int loudness) {
        memory.addLoudness(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), loudness));
        this.loudness = loudness;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
        if(health < 0)health = 0;
		if(health > 100)health = 100;
		if(health != this.health)creature.writer.condition.setHealth(health);
        memory.addHealth(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), health));
		this.health = health;
	}
	public int getFatigue() {
		if(creature.actualCondition.getFatigueMax() < fatigue)
			setFatigue(creature.actualCondition.getFatigueMax());
		return fatigue;
	}
	public void setFatigue(int fatigue) {
        if(fatigue > 100)fatigue = 100;
		if(fatigue < 0) {
			// v p��pad�, �e fatigue max bude ji� men��, jak 10, hr�� omdl�, i kdy� n�hl� energie m� hodn�
			if(creature.actualCondition.getFatigueMax() > 10) {
			/*	for(Behaviour behaviour : player.getCurrentBehaviour()) {
					Action action = player.game.calendar.findItemByBehaviour(behaviour, player.game);
					action.setDate(action.getDate() + 10); // p�i�te se k tomu 10 
				}*/

			}else {
				// behaviour mus� b�t p�eru�eno, proto�e player je p��li� unaven�
				// za�ne spinkat tam kde je
		/*		for(Behaviour behaviour : player.getCurrentBehaviour()) {
					player.game.calendar.interrupt(behaviour.getAction().getId());
					player.game.behavior.make_sleep(5 * 20, player);
				}*/
			}
			fatigue = 0;
		}
        memory.addFatigue(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), fatigue));
		this.fatigue = fatigue;
	}
	public int getAttention() {
		return attention;
	}

	public void setAttention(int attention) {
        if(attention < 0)attention = 0;
        memory.addAttention(new ObjectsMemoryCell<Integer>(creature.game.time.getTime(), attention));
		this.attention = attention;
	}
}
