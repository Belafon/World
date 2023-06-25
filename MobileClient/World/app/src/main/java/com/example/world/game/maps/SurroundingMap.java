package com.example.world.game.maps;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;

import com.example.world.game.maps.playersPlacePanels.PlaceInfoFragment;
import com.example.world.game.maps.playersPlacePanels.PlacePanel;

public class SurroundingMap {
    public static final int NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS = 7;
    private PlacePanel[][] places = new PlacePanel[NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS][NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS];

    public PlacePanel getPlacePanel(int x, int y) {
        return places[x][y];
    }

    public void updatePlace(int x, int y, PlacePanel place) {
        places[x][y] = place;
    }

    public void setPlaceUnknown(int x, int y) {
        places[x][y] = null;
    }
}
