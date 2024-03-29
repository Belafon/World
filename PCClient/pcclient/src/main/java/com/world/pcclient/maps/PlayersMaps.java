package com.world.pcclient.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.world.pcclient.Stats;
import com.world.pcclient.behaviours.BehavioursRequirement;
import com.world.pcclient.maps.playersPlacePanels.PlacePanel;
import com.world.pcclient.maps.playersPlacePanels.PlayersPlaceEffect;
import com.world.pcclient.maps.playersPlacePanels.PlayersPlaceInfoPanel;
import com.world.pcclient.maps.playersPlacePanels.TypePlace;
import com.world.pcclient.maps.weather.Weather;

public class PlayersMaps {
    private Hashtable<Integer, PlayersMap> maps = new Hashtable<>();
    private SurroundingMap surroundingMap;
    // private PlayersMap currentMap;
    // private PlacePanel currentPosition;

    private PlacePanel selectedPlacePanel = null;
    private PlayersPlaceInfoPanel infoPlacePanel = new PlayersPlaceInfoPanel("", "", new ArrayList<>());
    public final Weather weather = new Weather();

    public PlayersMaps() {
        surroundingMap = new SurroundingMap(infoPlacePanel);
    }

    /**
     * It points that there exists a map in the
     * world with these sizes and with concrete id.
     * 
     * @param key
     * @param map
     */
    public void addMap(int key, PlayersMap map) {
        maps.put(key, map);
        for (int x = 0; x < map.sizeX; x++) {
            for (int y = 0; y < map.sizeX; y++) {
                map.places[x][y] = new PlacePanel(x, y, infoPlacePanel);
            }
        }
    }

    /**
     * It points that there exists a map in the
     * world with these sizes and with concrete id.
     * 
     * @param args
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     */
    public void addMap(String[] args) throws NumberFormatException, IndexOutOfBoundsException {
        int id = Integer.parseInt(args[2]);
        int sizeX = Integer.parseInt(args[3]);
        int sizeY = Integer.parseInt(args[4]);
        PlayersMap map = new PlayersMap(id, sizeX, sizeY);
        addMap(id, map);
    }

    public void removeMap(int key) {
        maps.remove(key);
    }

    public PlayersMap getMap(int key) {
        return maps.get(key);
    }

    /**
     * This method updates arround places
     * with the message from the server.
     * It updates all the places.
     * 
     * @param args
     * @param stats
     */
    public synchronized void lookAroundSurroundingPlaces(String[] args, Stats stats) {
        int currentPlace = 0;
        for (int currentArg = 2; currentArg < args.length; currentArg++) {
            switch (args[currentArg]) {
                case "x" -> {
                    currentPlace = setSurroundingAtIdsNull(currentPlace, args[++currentArg]);
                    currentArg++;
                }
                default -> currentArg = setSurroundingAtId(currentPlace++, currentArg, args, stats);
            }
            ;
        }
    }

    private int setSurroundingAtId(int currentPlace, int currentArg, String[] args, Stats stats) {
        String[] typePlacesInfo = args[currentArg++].split("\\+");

        String typePlacesName = typePlacesInfo[0];
        String id = typePlacesInfo[1];


        // lets handle requirements 
        String requirementsMessage = null;
        if (typePlacesInfo.length > 2)
            requirementsMessage = typePlacesInfo[2];

        Set<BehavioursRequirement> requirements = null;
        if (requirementsMessage != null)
            requirements = getRequirementsFromMessage(requirementsMessage, stats);
        else
            requirements = new HashSet<>();


        // lets handle effects
        String effectsMessage = null;
        if (typePlacesInfo.length > 3)
            effectsMessage = typePlacesInfo[3];

        if (!TypePlace.allTypes.containsKey(typePlacesName))
            throw new IllegalArgumentException("unsupported type place name");

        int xInSurrounding = currentPlace / SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;
        int yInSurrounding = currentPlace % SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;

        List<PlayersPlaceEffect> effects = null;
        if (effectsMessage != null)
            effects = getPlaceEffectsFromMessage(effectsMessage,
                    surroundingMap.getPlacePanel(xInSurrounding, yInSurrounding));
        else
            effects = new ArrayList<>();


        // lets update the panel that shows the place in the surrounding
        Place place = new Place(id, TypePlace.allTypes.get(typePlacesName), requirements, effects);
        surroundingMap.updatePlace(xInSurrounding, yInSurrounding, place);

        // lets add the place into ingredients
        stats.visibles.addNewPlace(place);

        return currentArg;
    }

    private Set<BehavioursRequirement> getRequirementsFromMessage(String requirementsMessage, Stats stats) {
        Set<BehavioursRequirement> requirements = new HashSet<>();
        for (String requirement : requirementsMessage.split("\\,")) {
            requirements.add(stats.behaviours.allRequirements.get(requirement));
        }
        return requirements;
    }

    private List<PlayersPlaceEffect> getPlaceEffectsFromMessage(String effectsMessage, PlacePanel place) {
        List<PlayersPlaceEffect> effects = new ArrayList<>();
        for (String effect : effectsMessage.split("\\,")) {
            effects.add(PlayersPlaceEffect.allPlaceEffects.get(effect));
        }
        return effects;
    }

    private int setSurroundingAtIdsNull(int currentPlace, String count) throws NumberFormatException {
        int numberOfStackedNullPlaces = Integer.parseInt(count);
        for (int i = 0; i < numberOfStackedNullPlaces; i++) {
            int xInSurrounding = currentPlace / SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;
            int yInSurrounding = currentPlace % SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;
            surroundingMap.setPlaceUnknown(xInSurrounding, yInSurrounding, infoPlacePanel);
            currentPlace++;
        }
        return currentPlace;
    }

    /**
     * @return panel that shows all surrounding
     *         places around the creature in the radius
     *         of 3 places.
     *         The places, that are not visible for the
     *         player are displayed with grey color and
     *         with name as umknown place.
     */
    public SurroundingPlacesPanel getSurroundingPlacesPanel() {
        return new SurroundingPlacesPanel(surroundingMap.getComponent(), infoPlacePanel);
    }

    public void removePlaceInSight(String[] args, Stats stats) {
        String id = args[2];
        int x = Integer.parseInt(args[3]);
        int y = Integer.parseInt(args[4]);
        Place place = surroundingMap.getPlacePanel(x, y).getPlace();

        stats.visibles.removePlace(place);
        surroundingMap.setPlaceUnknown(x, y, infoPlacePanel);
    }

    /*
     * private int getYInMap(int yInSurrounding) {
     * return currentPosition.positionY - yInSurrounding - 3;
     * }
     * 
     * private int getXInMap(int xInSurrounding) {
     * return currentPosition.positionX - xInSurrounding - 3;
     * }
     */
}