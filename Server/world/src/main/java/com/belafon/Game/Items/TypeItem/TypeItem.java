package com.belafon.Game.Items.TypeItem;

import com.belafon.Game.Creatures.Behaviour.Behaviours.ConsumableBehavioursPossibleRequirement;

public abstract class TypeItem extends ConsumableBehavioursPossibleRequirement{
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
