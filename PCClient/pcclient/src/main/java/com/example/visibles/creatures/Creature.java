package com.example.visibles.creatures;

import com.example.visibles.Visible;

public class Creature extends Visible {

    private String look;
    private int id;
    public Creature(String name, int id, String look) {
        super(name);
        this.id = id;
        this.look = look;
    }

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
