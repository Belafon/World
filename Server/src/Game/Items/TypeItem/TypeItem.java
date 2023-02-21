package Game.Items.TypeItem;

import Game.Creatures.Behaviour.Behaviours.BehavioursProperty;

public abstract class TypeItem implements BehavioursProperty.Consumable{
    public final String name;
    public final int regularWeight;
    public final int toss; // > 0 -> throwable, more describes demage
    public final int visibility;
    public final String look;

    public TypeItem(String name, int regularWeight, int toss, int visibility, String look) {
        this.name = name;
        this.regularWeight = regularWeight;
        this.toss = toss;
        this.visibility = visibility;
        this.look = look;
    }
}
