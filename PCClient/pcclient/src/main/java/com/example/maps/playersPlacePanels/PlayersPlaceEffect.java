package com.example.maps.playersPlacePanels;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

public class PlayersPlaceEffect {
    public final static Map<String, PlayersPlaceEffect> allPlaceEffects;
    public final PlaceEffectName name;

    public PlayersPlaceEffect(PlaceEffectName name, String look) {
        this.name = name;
        this.look = look;
    }

    public final String look;
    static {
        allPlaceEffects = new HashMap<>();
        allPlaceEffects.put("fire", new PlayersPlaceEffect(PlaceEffectName.fire, "Fire with huge smoke"));
    }

    public static enum PlaceEffectName {
        fire
    }

    public JComponent getInfoPanel() {
        return new PlaceEffectInfoPanel(name, look);
    }
}
