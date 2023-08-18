package com.belafon.world.mobileClient.game.maps.playersPlaceFragments;

import android.graphics.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TypePlace {
    public final String name;
    public final String description;
    public final int backgroundColor;

    public TypePlace(String name, String description, int backgroundColor) {
        this.name = name;
        this.description = description;
        this.backgroundColor = backgroundColor;
    }

    public static final Map<String, TypePlace> allTypes;
    static {
        Map<String, TypePlace> types = new HashMap<>();
        types.put("mountein_meadow",
                new TypePlace("mountein_meadow", "description meadow",
                        Color.rgb(60, 120, 2)));

        types.put("meadow",
                new TypePlace("meadow", "description meadow",
                        Color.rgb(60, 120, 2)));

        types.put("forest_leafy",
                new TypePlace("forest_leafy", "description forest_leafy",
                        Color.rgb(10, 100, 10)));

        types.put("unknown",
                new TypePlace("unknown", "unknown place",
                        Color.rgb(100, 100, 100)));

        allTypes = Collections.unmodifiableMap(types);
    }
}
