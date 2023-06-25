package com.example.world.game.maps.playersPlacePanels;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * All possible place effects and their informations are held here 
 */
public class PlayersPlaceEffect {
    public final static Map<PlaceEffectName, PlayersPlaceEffect> allPlaceEffects;
    public final PlaceEffectName name;

    public PlayersPlaceEffect(PlaceEffectName name, String look) {
        this.name = name;
        this.look = look;
    }

    public final String look;
    static {
        allPlaceEffects = new HashMap<>();
        allPlaceEffects.put(PlaceEffectName.fire, new PlayersPlaceEffect(PlaceEffectName.fire, "Fire with huge smoke"));
    }

    public static enum PlaceEffectName {
        fire
    }

    /**
     * Retunrs a fragment with info about the PlaceEffect.
     * @return
     */
    public Fragment getInfoFragment(Fragment lastFragment, int container) {
        return new PlaceEffectInfoFragment(lastFragment, container, name, look);
    }
}
