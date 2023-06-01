package com.belafon.world.maps.place;

import com.belafon.world.maps.Map;
import com.belafon.world.maps.weather.Weather;
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

    public static final BehavioursPossibleRequirement REQUIREMENT_IS_REACHABLE = new BehavioursPossibleRequirement("An item is visible") {
    };

    private static Map printNewPlaceOnlyOfOneMap;

    public Place(Map map, int positionX, int positionY, int altitude, TypeOfPlace typeOfPlace) {
        super(typeOfPlace, map.game);
        if (printNewPlaceOnlyOfOneMap == null)
            printNewPlaceOnlyOfOneMap = map;
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
        int temperature = 30 - (altitude / 80) + map.game.time.partOfDay.temperatureChange(); // altitude = 1000, -> 30
                                                                                              // - (1000 / 80) = 30 - 12
                                                                                              // = 8 degrees celsius
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
        if (map == printNewPlaceOnlyOfOneMap)
            log += "Place: " + " in position = [" + positionX + ";" + positionY + "]  with altitude = "
                    + altitude + "  " + typeOfPlace.name + "]";
        log += "\t\tResources = [ ";
        for (Resource resource : resources.values())
            log += resource.type.name + " ";
        log += " ]";
        return log;
    }

}