package com.belafon.world.maps.placeEffects;

public class PlaceEffect {
    public final PlaceEffects.PlaceEffectName name;
	public final int visibility; // 0-100 -> 89 < je vyd�t z jin�ho place!!
	public final String look;
	public PlaceEffect(PlaceEffects.PlaceEffectName name, int visibility, String look) {
		this.name = name;
		this.look = look;
		this.visibility = visibility;
	}
}
