package Game.Creatures.Behaviour.Behaviours;

import java.util.ArrayList;

import Game.Game;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Maps.Place.Place;

public class Move extends Behaviour{
    public final Place destination;
    private ArrayList<Place> jurney = new ArrayList<Place>();
    private int currentPositionOfTravel = 0;
    public Move(Game game, Creature creature, Place destination) {
        super(game, 0, 0, creature, null);
        this.destination = destination;
        Place actualPlace = creature.getPosition();
        Place place = actualPlace;
        int x = place.positionX;
        int y = place.positionY;
        while(place != destination){
            if(x < destination.positionX) x++;
            else if(x > destination.positionX) x--;
            if(y < destination.positionY) y++;
            else if(y > destination.positionY) y--;
            place = destination.map.places[x][y];
            jurney.add(place);
        }
    }

    @Override
    public void execute() {
        cease();
    }

    @Override
    public void interrupt() {
        
    }

    @Override
    public void cease() {
        if(currentPositionOfTravel > 0) creature.setPosition(jurney.get(currentPositionOfTravel - 1));
        if(currentPositionOfTravel < jurney.size()){
            duration = getDurationOfTravel(creature);
            event = new EventBehaviour(game.time.getTime() + duration, game, this);
            creature.game.calendar.add(event);
            currentPositionOfTravel++;
        }
    }

    @Override
    public String canCreatureDoThis() {
        if(destination.map != creature.getPosition().map)return "Cant find the way! The destination is not in this map.";
        return null;
    }
    
    public int getDurationOfTravel(Creature creature) {
		float speed = getCurrentSpeed(creature);
		
		// draha / rychlost = cas (+ hodiny musim prevest na minuty) 
		// 20 jednotek = 1 hodina -> 1/3 jednotky za minutu
		float durationOfTravel = (1000f / speed) / 3f;
		
		// influence by health of player
		durationOfTravel += (150f - (2.1f * (float)creature.actualCondition.getHealth()) + (0.006f * ((float)creature.actualCondition.getHealth() * (float)creature.actualCondition.getHealth()))) / 3f;		
		return (int)durationOfTravel; 
	} 

    private float getCurrentSpeed(Creature creature) {
		Place nextPlace = jurney.get(currentPositionOfTravel);
		
		// influence by Object altitude;
		int differenceOfAltitudes = nextPlace.altitude - creature.getPosition().altitude;
		float averageRoadDegree = (float) Math.atan(((float)differenceOfAltitudes * 3f)/1000f);
		float averageSpeed = (float) (6 * Math.exp(-(3/2) * Math.abs(Math.tan(averageRoadDegree + (1/20))))); // Tobler's hiking function
		//averageSpeed *= (50/3); // translation killometers per hour to meters per minutes
		//averageSpeed *= (3/5); // hiking through the nature out of the road (its more difficult) -> 
		averageSpeed *= 10;
		return averageSpeed;
	}
}
