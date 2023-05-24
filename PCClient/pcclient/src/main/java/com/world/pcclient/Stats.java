package com.world.pcclient;

import com.world.pcclient.behaviours.Behaviours;
import com.world.pcclient.bodyStats.BodyStats;
import com.world.pcclient.inventory.Inventory;
import com.world.pcclient.maps.PlayersMaps;
import com.world.pcclient.visibles.Visibles;

public class Stats {
    public final BodyStats body = new BodyStats();
    public final PlayersMaps maps = new PlayersMaps();
    public final Inventory inventory = new Inventory();
    public final Visibles visibles = new Visibles();
    public final Behaviours behaviours = new Behaviours();
}
