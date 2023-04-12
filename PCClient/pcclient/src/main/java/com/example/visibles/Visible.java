package com.example.visibles;

public abstract class Visible {
    public final String name;

    public Visible(String name) {
        this.name = name;
    }

    public abstract Runnable getOnTitleClick();
}
