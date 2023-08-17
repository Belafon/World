package com.example.world.game.maps;

import com.example.world.game.maps.playersPlaceFragments.PlaceFragment;

public class PlayersMap {
    public final int id;
    public final int sizeX;
    public final int sizeY;
    public final PlaceFragment[][] places;

    public PlayersMap(int id, int sizeX, int sizeY) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        places = new PlaceFragment[sizeX][sizeY];
    }
}
