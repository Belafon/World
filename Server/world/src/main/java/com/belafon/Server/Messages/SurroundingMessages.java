package com.belafon.Server.Messages;

import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Resources.Resource;
import com.belafon.Game.Maps.Weather.Weather;

public abstract  class SurroundingMessages {
    public abstract void setPartOfDay(String partOfDay);
    public abstract void setWeather(Weather weather);
    public abstract void setClouds(Place place);
    public abstract void setPosition(Place position);
	public abstract void setInfoAboutPlace(Place place);
	public abstract void setInfoAboutSurrounding(Place position);
    public abstract void setResources(Place position);
	public abstract void setResource(Resource resource);
	public abstract void setResourceNotFound(Resource resource);
	public abstract void setItems(Place position);
	public abstract void setInfoAboutSurroundingLookAround(Place position);
	public abstract void setTypeOfPlaceInfoDrawableSound(Place position);
}
