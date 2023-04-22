package com.belafon.world.visibles;

/**
 * Class for generating unique ids. 
 * The instance is located in World object,
 * so each world has its own ids.
 */
public class VisibleIDs {
    private int nextCreatureId = 0;
    public synchronized int getCreatureId() {
        return nextCreatureId++;
    }

    private int nextItemId = 0;
    public synchronized int getItemId() {
        return nextItemId++;
    }

    private int nextResourceId = 0;
    public synchronized int getResourceId() {
        return nextResourceId++;
    }
    
    private int nextPlaceId = 0;

    public int getPlaceId() {
        return nextPlaceId++;
    }
    
    private int nextEventId = 0;
    public int getEventId() {
        return nextEventId++;
    }

}
