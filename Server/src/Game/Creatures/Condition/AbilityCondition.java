package Game.Creatures.Condition;

public class AbilityCondition {
	private int strength;
	private int agility;
	private int speedOfWalk;
	private int speedOfRun;
	private int currentSpeed;
	private int hearing;
	private int observation;
	private int vision;
	public AbilityCondition(int strength, int agility, int speed_of_walk
			, int speed_of_run, int hearing, int observation, int vision) {
		this.strength = strength;
		this.agility = agility;
		this.speedOfRun = speed_of_run;
		this.speedOfWalk = speed_of_walk;
		this.hearing = hearing;
		this.observation = observation;
		this.vision = vision;
		this.currentSpeed = this.speedOfWalk;
	}
	public synchronized int getStrength() {
		return strength;
	}
	public synchronized void setStrength(int strength) {
		this.strength = strength;
	}
	public synchronized int getAgility() {
		return agility;
	}
	public synchronized void setAgility(int agility) {
		this.agility = agility;
	}
	public synchronized int getSpeedOfWalk() {
		return speedOfWalk;
	}
	public synchronized void setSpeedOfWalk(int speedOfWalk) {
		this.speedOfWalk = speedOfWalk;
	}
	public synchronized int getSpeedOfRun() {
		return speedOfRun;
	}
	public synchronized void setSpeedOfRun(int speedOfRun) {
		this.speedOfRun = speedOfRun;
	}
	public synchronized int getHearing() {
		return hearing;
	}
	public synchronized void setHearing(int hearing) {
		this.hearing = hearing;
	}
	public synchronized int getObservation() {
		return observation;
	}
	public synchronized void setObservation(int observation) {
		this.observation = observation;
	}
	public synchronized int getVision() {
		return vision;
	}
	public synchronized void setVision(int vision) {
		this.vision = vision;
	}
	public int getCurrent_speed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	
}
