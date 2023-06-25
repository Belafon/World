package com.example.world.game.maps.playersPlacePanels;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.maps.Place;
import com.example.world.game.maps.SurroundingMap;
import com.example.world.game.maps.SurroundingPlacesFragment;

import java.util.List;
import java.util.Set;

public class PlacePanel extends Place {
    public final int positionX;
    public final int positionY;

    public PlacePanel(String id, TypePlace typePlace, Set<BehavioursRequirement> requirements,
            List<PlayersPlaceEffect> placeEffects, int positionX, int positionY) {
        super(id, typePlace, requirements, placeEffects);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static PlacePanel getUnknownPlace(int x, int y) {
        return new PlacePanel(UNKNOWN.getId(), UNKNOWN.typePlace, UNKNOWN.requirements, UNKNOWN.placeEffects, x, y);
    }
}
