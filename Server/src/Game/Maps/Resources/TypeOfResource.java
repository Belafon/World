package Game.Maps.Resources;

import Game.Maps.PlaceEffects.PlaceEffect;

public class TypeOfResource {
    // final stats
    public final String name;
    public final PlaceEffect[] effects;
    public final int conspicuousness; // (in cz "nápadnoost"), than with more resources with heigher consp., to find the resource it takes more time (viz. speedOfFinding in Resource)   
    public TypeOfResource(String name, PlaceEffect[] effects, int speedOfFinding) {
        this.name = name;
        this.effects = effects;
        this.conspicuousness = speedOfFinding;
    }
}
