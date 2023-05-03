package com.world.pcclient.maps.playersPlacePanels;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

/** 
 * All possible place effects and their informations are held here 
 */
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

    /**
     * Retunrs a panel with info about the PlaceEffect.
     * @return
     */
    public JComponent getInfoPanel() {
        return new PlaceEffectInfoPanel(name, look);
    }
}
