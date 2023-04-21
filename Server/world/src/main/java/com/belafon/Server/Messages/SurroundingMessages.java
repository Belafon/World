package com.belafon.Server.Messages;

import com.belafon.Game.Maps.Map;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Weather.Weather;
import com.belafon.Game.ObjectsMemory.Visible;
import com.belafon.Game.Time.DailyLoop.NamePartOfDay;

public interface SurroundingMessages {
    public default void setPartOfDay(NamePartOfDay partOfDay) {
    }

    public default void setWeather(Weather weather) {
    }

    public default void setClouds(Place place) {
    }

    public default void setPosition(Place position) {
    }

    public default void setInfoAboutPlace(Place place) {
    }

    public default void setInfoAboutSurrounding(UnboundedPlace position) {
    }

    public default void setResources(UnboundedPlace position) {
    }

    public default void setResource(Resource resource) {
    }

    public default void setResourceNotFound(Resource resource) {
    }

    public default void setItems(UnboundedPlace position) {
    }

    public default void setInfoAboutSurroundingPlacesLookAround(Place position) {
    }

    public default void setTypeOfPlaceInfoDrawableSound(UnboundedPlace position) {
    }

    public default void setNewMap(Map map, int sizeX, int sizeY) {
    }

    public default void addVisibleInSight(Visible value) {
    }

    public default void removeVisibleFromSight(Visible value) {
    }
}
