package com.world.pcclient.visibles.creatures;

import java.util.Set;

import com.world.pcclient.behaviours.Behaviour;
import com.world.pcclient.visibles.Visible;

public class Creature extends Visible {

    private String look;
    private int id;

    public Creature(String name, int id, String look, Set<Behaviour> behaviours) {
        super(behaviours, name);
        this.id = id;
        this.look = look;
    }

    // TODO not implemented yet
    @Override
    public Runnable getOnTitleClick() {
        return () -> {};
    }

    public int getId() {
        return id;
    }

    public String getLook() {
        return look;
    }

}
