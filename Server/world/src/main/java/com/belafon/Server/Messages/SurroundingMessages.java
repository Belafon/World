package com.belafon.Server.Messages;

import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Weather.Weather;

public interface SurroundingMessages {
    public default void setPartOfDay(String partOfDay) {
    }

    public default void setWeather(Weather weather) {
    }

    public default void setClouds(Place place) {
    }

    public default void setPosition(Place position) {
    }

    public default void setInfoAboutPlace(Place place) {
    }

    public default void setInfoAboutSurrounding(Place position) {
    }

    public default void setResources(Place position) {
    }

    public default void setResource(Resource resource) {
    }

    public default void setResourceNotFound(Resource resource) {
    }

    public default void setItems(Place position) {
    }

    public default void setInfoAboutSurroundingPlacesLookAround(Place position) {
    }

    public default void setTypeOfPlaceInfoDrawableSound(Place position) {
    }

    public default void setNewMap(int hashCode, int sizeX, int sizeY) {
    }
}
