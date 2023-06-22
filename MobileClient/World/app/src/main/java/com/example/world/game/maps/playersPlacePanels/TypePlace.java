package com.example.world.game.maps.playersPlacePanels;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TypePlace {
    public final String name;
    public final String description;
    public final Color backgroundColor;

    public TypePlace(String name, String description, Color backgroundColor) {
        this.name = name;
        this.description = description;
        this.backgroundColor = backgroundColor;
    }
    
    public static final Map<String, TypePlace> allTypes;
    static {
        Map<String, TypePlace> types = new HashMap<>();
        types.put("mountein_meadow",
            new TypePlace("mountein_meadow", "description meadow",
                    new Color(60, 120, 2)));

        types.put("meadow",
            new TypePlace("meadow", "description meadow",
                    new Color(60, 120, 2)));
                    
        types.put("forest_leafy",
            new TypePlace("forest_leafy", "description forest_leafy",
                    new Color(10, 100, 10)));

        types.put("unknown",
            new TypePlace("unknown", "unknown place",
                    new Color(100, 100, 100)));

        allTypes = Collections.unmodifiableMap(types);
    }
    
}
