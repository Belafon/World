package com.belafon.world.visibles.creatures.behaviour.behaviours;

import java.util.ArrayList;
import java.util.Map;

import com.belafon.world.World;
import com.belafon.world.calendar.events.EventBehaviour;
import com.belafon.world.maps.place.Place;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.Behaviour;
import com.belafon.world.visibles.creatures.behaviour.BehaviourType;
import com.belafon.world.visibles.creatures.behaviour.BehaviourTypeBuilder;
import com.belafon.world.visibles.creatures.behaviour.BehaviourType.IngredientsCounts;

public class Move extends Behaviour {
    public final Place destination;
    private ArrayList<Place> jurney = new ArrayList<Place>();
    private int currentPositionOfTravel = 0;
    
    public static final BehaviourType type; 
    static {
        type = new BehaviourTypeBuilder(Move.class)
            .build();
    }

    public Move(World game, Creature creature, Place destination) {
        super(game, 0, 0, creature);
        this.destination = destination;
        UnboundedPlace unboundedPlace = creature.getLocation();
        if(unboundedPlace instanceof Place place){
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
        } else {
            // TODO
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
        if (currentPositionOfTravel > 0)
            creature.setLocation(jurney.get(currentPositionOfTravel - 1));
        if(currentPositionOfTravel < jurney.size()){
            duration = getDurationOfTravel(creature);
            event = new EventBehaviour(game.time.getTime() + duration, game, this);
            creature.game.calendar.add(event);
            currentPositionOfTravel++;
        }
    }

    @Override
    public String canCreatureDoThis() {
        if (creature.getLocation() instanceof Place place) {
            if (destination.map != place.map)
                return "cant_find_the_way!_The_destination_is_not_in_this_map.";
        }
        return null; // TODO
    }
    
    public int getDurationOfTravel(Creature creature) {
		float speed = getCurrentSpeed(creature);
		
		// distance / speed = time (+ hours have to be transfered to minutes) 
		// 20 jednotek = 1 hodina -> 1/3 jednotky za minutu
		float durationOfTravel = 1000f / speed;
		
		// influence by health of player
        durationOfTravel += 150f - (2.1f * (float) creature.abilityCondition.getHealth()) + (0.006f
                * ((float) creature.abilityCondition.getHealth() * (float) creature.abilityCondition.getHealth()));

		return (int)durationOfTravel; 
	} 

    private float getCurrentSpeed(Creature creature) {
        if (creature.getLocation() instanceof Place place) {
            Place nextPlace = jurney.get(currentPositionOfTravel);
            
            // influence by Object altitude;
            int differenceOfAltitudes = nextPlace.altitude - place.altitude;
            float averageRoadDegree = (float) Math.atan(((float)differenceOfAltitudes * 3f)/1000f);
            float averageSpeed = (float) (6 * Math.exp(-(3/2) * Math.abs(Math.tan(averageRoadDegree + (1/20))))); // Tobler's hiking function
            //averageSpeed *= (50/3); // translation killometers per hour to meters per minutes
            //averageSpeed *= (3/5); // hiking through the nature out of the road (its more difficult) -> 
            averageSpeed *= 10;
            return averageSpeed;
        } else {
            // TODO
        }
        return 0f;
	}

    @Override
    public Map<BehavioursPossibleRequirement, IngredientsCounts> getRequirements() {
        return type.requirements;
    }

    @Override
    public BehaviourType getType() {
        return type;
    }
}
