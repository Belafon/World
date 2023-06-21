package com.world.pcclient.maps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.world.pcclient.behaviours.BehavioursRequirement;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.world.pcclient.maps.playersPlacePanels.PlacePanel;
import com.world.pcclient.maps.playersPlacePanels.PlayersPlaceEffect;
import com.world.pcclient.maps.playersPlacePanels.TypePlace;

public class Place extends BehavioursPossibleIngredient {
    public static final Place UNKNOWN = new Place("UnboundedPlace|unknown$" + Integer.MIN_VALUE, TypePlace.allTypes.get("unknown"),
            new HashSet<>(), new ArrayList<>());
    public PlacePanel panel;
    public final int id;
    public final String mapId;
    public TypePlace typePlace;
    public List<PlayersPlaceEffect> placeEffects;

    public Place(String id, TypePlace typePlace, Set<BehavioursRequirement> requirements,
            List<PlayersPlaceEffect> placeEffects) {
        super(requirements);
        var idTypeSplit = id.split("\\|");
        var idSplit = idTypeSplit[1].split("\\$");
        this.mapId = idSplit[0];
        this.id = Integer.parseInt(idSplit[1]);
        this.typePlace = typePlace;
        this.placeEffects = placeEffects;
    }

    @Override
    protected String getName() {
        if (panel != null)
            return typePlace.name + " [" + panel.positionX + ";" + panel.positionY + "]";
        return typePlace.name;
    }

    @Override
    public String getId() {
        return mapId + "$" + id;
    }

    @Override
    public String getVisibleType() {
        return "UnboundedPlace";
    }

    public void setPanel(PlacePanel placePanel) {
        this.panel = placePanel;
    }

}
