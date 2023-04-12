package com.example;

import com.example.bodyStats.BodyStats;
import com.example.inventory.Inventory;
import com.example.maps.PlayersMaps;
import com.example.visibles.Visibles;

public class Stats {
    public final BodyStats body = new BodyStats();
    public final PlayersMaps maps = new PlayersMaps();
    public final Inventory inventory = new Inventory();
    public final Visibles visibles = new Visibles();
}
