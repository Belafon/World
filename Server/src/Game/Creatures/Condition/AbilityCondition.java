package Game.Creatures.Condition;

import Game.Creatures.Creature;

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
	Creature creature;
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
	public synchronized void setCurrentEnergyOutput(int currentEnergyOutput) {
		this.currentEnergyOutput = currentEnergyOutput;
	}
	public int getStrength() {
		return strength;
	}
	public synchronized void setStrength(int strength) {
		this.strength = strength;
	}
	public int getAgility() {
		return agility;
	}
	public synchronized void setAgility(int agility) {
		this.agility = agility;
	}
	public int getSpeedOfWalk() {
		return speedOfWalk;
	}
	public synchronized void setSpeedOfWalk(int speedOfWalk) {
		this.speedOfWalk = speedOfWalk;
	}
	public int getSpeedOfRun() {
		return speedOfRun;
	}
	public synchronized void setSpeedOfRun(int speedOfRun) {
		this.speedOfRun = speedOfRun;
	}
	public int getHearing() {
		return hearing;
	}
	public synchronized void setHearing(int hearing) {
		this.hearing = hearing;
	}
	public int getObservation() {
		return observation;
	}
	public synchronized void setObservation(int observation) {
		this.observation = observation;
	}
	public int getVision() {
		return vision;
	}
	public synchronized void setVision(int vision) {
		this.vision = vision;
	}
	public int getCurrent_speed() {
		return currentSpeed;
	}
	public synchronized void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	public int getLoudness() {
		return loudness;
	}
	public synchronized void setLoudness(int loudness) {
		this.loudness = loudness;
	}
	public synchronized int getHealth() {
		return health;
	}
	public synchronized void setHealth(int health) {
		if(health < 0)health = 0;
		if(health > 100)health = 100;
		if(health != this.health)creature.writer.condition.setHealth(health);
		this.health = health;
	}
	public synchronized int getFatigue() {
		if(creature.actualCondition.getFatigueMax() < fatigue)
			setFatigue(creature.actualCondition.getFatigueMax());
		return fatigue;
	}
	public synchronized void setFatigue(int fatigue) {
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
		this.fatigue = fatigue;
	}
	public synchronized int getAttention() {
		return attention;
	}

	public synchronized void setAttention(int attention) {
		if(attention < 0)attention = 0;
		this.attention = attention;
	}
}
