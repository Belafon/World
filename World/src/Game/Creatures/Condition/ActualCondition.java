package Game.Creatures.Condition;

import Game.Creatures.Creature;
import Game.Creatures.Player;
import Game.Game;
import Game.Calendar.Events.EventCreatureActualCondition;

public class ActualCondition{
	private int health;
	private int hunger;
	private EventCreatureActualCondition eventHunger;
	private int fatigue; // �nava 0 - 100 (0 - unaven naprosto)
	private EventCreatureActualCondition eventFatigue;
	private int fatigueMax; // = bariera pro odpo�inek, v��e zv��it fatigue nem��e, jedin� sp�nkem 
	//private int energy; // - > d� se snadno na�erpat odpo�inkem, nap�. p�i boji  --- nen� t�eba
	private int heat;   
	private EventCreatureActualCondition eventHeat;
	private int loudness;  
	private int visibility; // nespecifikov�no
	private int poison;           
	private EventCreatureActualCondition eventPoison;
	private int bleeding; // krv�cen�
	private EventCreatureActualCondition eventBleeding;
	private int attention;
	//private ArrayList<Disease> diseases;
	//private ArrayList<Injury> injuries;
	private Creature creature;
	public ActualCondition(Creature creature) {
		this.creature = creature;
		hunger = 100;
		fatigue = 100;
		fatigueMax = 100;
		bleeding = 100;
		//diseases = new ArrayList<>();
		//injuries = new ArrayList<>();
		heat = 100;
		poison = 0;
		health = 100;
		attention = 100;
		visibility = 100;
		loudness = 100;
		Game game = creature.game;
		game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventHunger));
		game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventFatigue));
		game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventHeat));
		game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventPoison));
		game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventBleeding));
	}

	public void setEventHunger(Object object){
		setHunger(getHunger() - 1);
		Game game = creature.game;
		int duration = 0;
		
		if(hunger != 0)game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventHunger));
	}
	public void setEventFatigue(Object object){
		setFatigue(getFatigue() - 1);
		Game game = creature.game;
		
		if(fatigue != 0)game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventFatigue));
	}
	public void setEventHeat(Object object){
		setHeat(getHeat() - 1);
		Game game = creature.game;
		if(heat != 0)game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventHeat));
	}
	public void setEventPoison(Object object){
		setHealth(getHealth() - 1);
		setPoison(getPoison() - 1);
		Game game = creature.game;
		if(poison != 0)game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventPoison));
	}
	public void setEventBleeding(Object object){
		setHealth(getHealth() - 1);
		setBleeding(getBleeding() - 1);
		Game game = creature.game;
		if(bleeding != 0)game.calendar.add(new EventCreatureActualCondition(game.time.getTime(), creature.game, this::setEventBleeding));
	}

	public synchronized int getAttention() {
		return attention;
	}

	public synchronized void setAttention(int attention) {
		if(attention < 0)attention = 0;
		this.attention = attention;
	}
	
	public synchronized int getHealth() {
		return health;
	}
	public synchronized void setHealth(int health) {
		// kontorla zda-li hr�� neum�el
		if(this.health <= 0)health = 0;
		//if(this.health <= 0)System.out.println("Hrac umrel / =" + this.health);
		if(health > 100)health = 100;
		if(creature instanceof Player)((Player)creature).client.writer.setHealth(health);
		this.health = health;
	}
	public synchronized int getHunger() {
		return hunger;
	}
	public synchronized void setHunger(int hunger) {
		if(hunger > 100)hunger = 100;
		if(hunger < 0) {
			setHealth(creature.actualCondition.getHealth() + hunger);
			hunger = 0;
		}
		if(creature instanceof Player)((Player)creature).client.writer.setHunger(hunger);
		this.hunger = hunger;
	}
	public synchronized int getFatigue() {
		return fatigue;
	}
	public synchronized void setFatigue(int fatigue) {
		if(fatigue > 100)fatigue = 100;
		if(fatigue < 0) {
			// v p��pad�, �e fatigue max bude ji� men��, jak 10, hr�� omdl�, i kdy� n�hl� energie m� hodn�
			if(getFatigueMax() > 10) {
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
	public synchronized int getBleeding() {
		return bleeding;
	}
	public synchronized void setBleeding(int bleeding) {
		if(bleeding > 100)bleeding = 100;
		if(bleeding < 0)bleeding = 0;
		if(creature instanceof Player)((Player)creature).client.writer.setBleeding(bleeding);
		this.bleeding = bleeding;
	}
	public synchronized int getHeat() {
		return heat;
	}
	public synchronized void setHeat(int heat) {
		if(heat > 100)heat = 100;
		if(heat < 0)heat = 0;
		if(creature instanceof Player)((Player)creature).client.writer.setHeat(heat);
		this.heat = heat;
	}
	public synchronized int getPoison() {
		return poison;
	}
	public synchronized void setPoison(int poison) {
		if(poison > 100)poison = 100;
		if(poison < 0)poison = 0;
		this.poison = poison;
	}
	synchronized public int getLoudness() {
		return loudness;
	}
	synchronized public void setLoudness(int loudness) {
		this.loudness = loudness;
	}
	synchronized public int getVisibility() {
		return visibility;
	}
	synchronized public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public synchronized int getFatigueMax() {
		return fatigueMax;
	}
	public synchronized void setFatigueMax(int fatigueMax, Game game) {
		if(fatigueMax > 100)fatigueMax = 100;
		if(fatigueMax < 0) {
			/*for(Behaviour behaviour : Creature.getCurrentBehaviour()) {
				game.calendar.deletePlayersCurrentBehaviourAction(Creature, Creature.getBehaviourPositionInArray(behaviour));
				game.behavior.make_sleep(5 * 20, Creature);
			}*/
			fatigueMax = 0;
		}
		if(creature instanceof Player)((Player)creature).client.writer.setFatigueMax(fatigueMax);
		this.fatigueMax = fatigueMax;
	}
}
