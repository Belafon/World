package com.belafon.world.maps.place;

import java.util.List;

import com.belafon.world.maps.Map;
import com.belafon.world.maps.weather.Weather;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.BehavioursPossibleIngredientID;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.visibles.resources.Resource;

/**
 * this is an UnboundedPlace in concrete map.
 */
public class Place extends UnboundedPlace {
    public final Map map;
    public final int positionX;
    public final int positionY;
    public int altitude;

    public static final BehavioursPossibleRequirement REQUIREMENT_IS_REACHABLE = new BehavioursPossibleRequirement(
            "A place is reachable.") {
    };

    public Place(Map map, int positionX, int positionY, int altitude, TypeOfPlace typeOfPlace) {
        super(typeOfPlace, map.game);
        
        this.map = map;
        this.positionX = positionX;
        this.positionY = positionY;
        this.altitude = altitude;
    }

    /**
     * returns probable temerature of the place.
     * There is no random.
     */
    public int getTemperature() {
        // altitude = 1000, -> 30 - (1000 / 80) = 30 - 12
        // = 8 degrees celsius
        int temperature = 30 - (altitude / 80) + map.game.time.partOfDay.temperatureChange();
        if (map.sky != null) {
            Weather weather = map.sky.getWeather(this);
            temperature -= weather.getClouds();
            temperature -= weather.getWeather();
        }
        return temperature;
    }

    @Override
    public int getVisibility() {
        return visibility + map.sky.getWeather(this).getVisibility();
    }

    public String log() {
        String log = "\n";
        log += "Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = "
                + altitude + "  " + typeOfPlace.name + "]";
        log += "\t\tResources = [ ";
        for (Resource resource : resources.values())
            log += resource.type.name + " ";
        log += " ]";
        return log;
    }

    @Override
    public List<BehavioursPossibleRequirement> getBehavioursPossibleRequirementType(Creature creature) {
        var list = super.getBehavioursPossibleRequirementType(creature);
        if (this != creature.getLocation()){
            if (creature.surroundingPlaces == null)
                list.add(REQUIREMENT_IS_REACHABLE);
            else if (creature.surroundingPlaces.isPlaceVisible(this))
                list.add(REQUIREMENT_IS_REACHABLE);
        }
        return list;
    }

    @Override
    public String getId() {
        return map.id + "$" + id;
    }

    @Override
    public BehavioursPossibleIngredientID getBehavioursPossibleIngredientID() {
        return new BehavioursPossibleIngredientID(UnboundedPlace.class, getId());
    }
}