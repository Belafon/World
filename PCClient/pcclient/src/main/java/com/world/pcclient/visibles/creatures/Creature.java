package com.world.pcclient.visibles.creatures;

import java.util.Set;

import com.world.pcclient.behaviours.BehavioursRequirement;
import com.world.pcclient.visibles.Visible;

public class Creature extends Visible {

    private String look;
    private final int id;

    public Creature(String name, int id, String look,
            Set<BehavioursRequirement> requirements) {
        super(requirements, name);

        this.id = id;
        this.look = look;
    }

    // TODO not implemented yet
    @Override
    public Runnable getOnTitleClick() {
        return () -> {
        };
    }

    @Override
    public String getId() {
        return "" + id;
    }

    public String getLook() {
        return look;
    }

    @Override
    public String getVisibleType() {
        return "Creature";
    }

}
