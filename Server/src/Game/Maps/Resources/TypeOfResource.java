package Game.Maps.Resources;

import Game.Maps.PlaceEffects.PlaceEffect;

public class TypeOfResource {
    // final stats
    public final String name;
    public final PlaceEffect[] effects;
    public final short speedOfFinding; // 0 - 100
    public TypeOfResource(String name, PlaceEffect[] effects, short speedOfFinding) {
        this.name = name;
        this.effects = effects;
        this.speedOfFinding = speedOfFinding;
    }
}
