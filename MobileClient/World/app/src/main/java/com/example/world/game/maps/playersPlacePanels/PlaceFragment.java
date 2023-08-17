package com.example.world.game.maps.playersPlaceFragments;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.example.world.game.maps.Place;
import com.example.world.game.maps.SurroundingMap;
import com.example.world.game.maps.SurroundingPlacesFragment;

import java.util.List;
import java.util.Set;

public class PlaceFragment extends Place {
    public final int positionX;
    public final int positionY;

    public PlaceFragment(String id, TypePlace typePlace, Set<BehavioursRequirement> requirements,
            List<PlayersPlaceEffect> placeEffects, int positionX, int positionY) {
        super(id, typePlace, requirements, placeEffects);
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static PlaceFragment getUnknownPlace(int x, int y) {
        return new PlaceFragment(UNKNOWN.getId(), UNKNOWN.typePlace, UNKNOWN.requirements, UNKNOWN.placeEffects, x, y);
    }

    @Override
    public int compareTo(BehavioursPossibleIngredient other) {
        if(other instanceof PlaceFragment otherPlace){
            if(positionX == otherPlace.positionX
                    && positionY == otherPlace.positionY)
                return 0;

            if(positionX < otherPlace.positionX)
                return -1;

            if(positionX == otherPlace.positionX
                    && positionY < otherPlace.positionY)
                return -1;
            return 1;
        } else throw new UnsupportedOperationException();
    }
}
