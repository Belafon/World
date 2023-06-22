package com.example.world.game;

import com.example.world.game.behaviours.Behaviours;
import com.example.world.game.bodyStats.BodyStats;
import com.example.world.game.inventory.Inventory;
import com.example.world.game.maps.PlayersMaps;
import com.example.world.game.visibles.Visibles;

public class Stats {
    public final BodyStats body = new BodyStats();
    public final PlayersMaps maps = new PlayersMaps();
    public final Inventory inventory = new Inventory();
    public final Visibles visibles = new Visibles();
    public final Behaviours behaviours = new Behaviours();
}
