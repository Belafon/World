package com.example.world.game;

import com.example.world.game.behaviours.BehavioursFragment;
import com.example.world.game.bodyStats.CreatureStatisticsFragment;
import com.example.world.game.inventory.fragments.InventoryFragment;
import com.example.world.game.maps.SurroundingPlacesFragment;
import com.example.world.game.visibles.VisiblesFragment;
import com.example.world.gameActivity.GameActivity;
import com.example.world.gameActivity.ViewFragment;

public class Fragments {
    public final CreatureStatisticsFragment bodyStatistics;
    public final SurroundingPlacesFragment surroundingPlaces;
    public final VisiblesFragment visibles;
    public final BehavioursFragment behaviours;
    public final ViewFragment view;
    public final Stats stats;
    public final InventoryFragment inventory;

    GameActivity gameActivity;

    public Fragments(Stats stats, GameActivity gameActivity) {
        this.stats = stats;
        this.gameActivity = gameActivity;
        this.bodyStatistics = new CreatureStatisticsFragment(stats.body);
        this.surroundingPlaces = stats.maps.getSurroundingPlacesFragment(null, gameActivity.getGameFragmentContainerID());
        //this.visibleItems = new ItemFragment(gameActivity.getGameFragmentContainerID());
        //this.visibleCreatures = new CreaturesFragment(gameActivity.getGameFragmentContainerID());
        //this.visibleResources = new ResourcesFragment(gameActivity.getGameFragmentContainerID());
        this.visibles = new VisiblesFragment(stats.visibles);
        this.view = new ViewFragment();
        // this.weatherFragment = stats.maps.weather.getFragment();
        this.behaviours = stats.behaviours.getNewBehaviourListFragment(gameActivity.getGameFragmentContainerID());
        this.inventory = new InventoryFragment(stats.inventory, gameActivity.getGameFragmentContainerID(), this);
    }
}
