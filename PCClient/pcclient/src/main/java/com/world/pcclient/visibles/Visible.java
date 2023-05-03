package com.world.pcclient.visibles;

public abstract class Visible {
    public final String name;

    public Visible(String name) {
        this.name = name;
    }

    /**
     * @return method that should be called 
     * when a title panel in list of visibles
     * is clicked.
     */
    public abstract Runnable getOnTitleClick();
}
