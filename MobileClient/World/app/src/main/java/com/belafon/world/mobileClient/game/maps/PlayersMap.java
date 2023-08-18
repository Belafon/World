package com.example.world.game.maps;

import com.example.world.game.maps.playersPlaceFragments.PlacePanel;

public class PlayersMap {
    public final int id;
    public final int sizeX;
    public final int sizeY;
    public final PlacePanel[][] places;

    public PlayersMap(int id, int sizeX, int sizeY) {
        this.id = id;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        places = new PlacePanel[sizeX][sizeY];
    }
}
