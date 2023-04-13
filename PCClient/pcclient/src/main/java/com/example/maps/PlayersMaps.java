package com.example.maps;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.example.maps.playersPlacePanels.PlacePanel;
import com.example.maps.playersPlacePanels.PlayersPlaceEffect;
import com.example.maps.playersPlacePanels.PlayersPlaceInfoPanel;
import com.example.maps.playersPlacePanels.TypePlace;
import com.example.maps.weather.Weather;

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
    public void addMap(int key, PlayersMap map) {
        maps.put(key, map);
        for (int x = 0; x < map.sizeX; x++) {
            for (int y = 0; y < map.sizeX; y++) {
                map.places[x][y] = new PlacePanel(x, y, infoPlacePanel);
            }
        }
    }

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

    public synchronized void lookAroundSurroundingPlaces(String[] args) {
        int currentPlace = 0;
        for (int currentArg = 2; currentArg < args.length; currentArg++) {
            switch (args[currentArg]) {
                case "x" -> {
                    currentPlace = setSurroundingAtIdsNull(currentPlace, args[++currentArg]);
                    currentArg++;
                }
                default -> currentArg = setSurroundingAtId(currentPlace++, currentArg, args);
            }
            ;
        }

    }

    private int setSurroundingAtId(int currentPlace, int currentArg, String[] args) {
        String typePlacesName = args[currentArg];

        if (!TypePlace.allTypes.containsKey(typePlacesName))
            throw new IllegalArgumentException("unsupported type place name");

        int xInSurrounding = currentPlace / SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;
        int yInSurrounding = currentPlace % SurroundingMap.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS;

        var effects = getPlaceEffects(++currentArg, args, surroundingMap.getPlacePanel(xInSurrounding, yInSurrounding));

        surroundingMap.updatePlace(TypePlace.allTypes.get(typePlacesName),
                xInSurrounding, yInSurrounding, effects, SurroundingMap.getOnPlaceSelected(infoPlacePanel));
        return currentArg;
    }

    private List<PlayersPlaceEffect> getPlaceEffects(int currentArg, String[] args, PlacePanel place) {
        List<PlayersPlaceEffect> effects = new ArrayList<>();
        for (int i = currentArg; i < args.length; i++) {
            if (args[i].equals(";"))
                break;
            var effect = PlayersPlaceEffect.allPlaceEffects.get(args[i]);
            if (effect == null)
                throw new IllegalArgumentException("Unknown PlaceEffect name \'" + args[i] + "\'");
            effects.add(effect);
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

    public SurroundingPlacesPanel getSurroundingPlacesPanel() {
        return new SurroundingPlacesPanel(surroundingMap.getComponent(), infoPlacePanel);
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