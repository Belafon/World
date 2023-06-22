package com.example.world.game.visibles;

import java.util.Set;

import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public class TypeOfResource extends BehavioursPossibleIngredient {
    public final String name;
    public final String id;

    public TypeOfResource(String name, String id, Set<BehavioursRequirement> requirements) {
        super(requirements);
        this.name = name;
        this.id = id;
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    public String getId() {
        return " " + name.hashCode();
    }

    @Override
    public String getVisibleType() {
        return "TypeOfResource";
    }

    @Override
    public String getMessageId(){
        return id;
    }

}
