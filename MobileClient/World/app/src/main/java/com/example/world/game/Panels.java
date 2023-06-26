package com.example.world.game;

import com.example.world.game.behaviours.BehavioursFragment;
import com.example.world.game.bodyStats.CreatureStatisticsPanel;
import com.example.world.game.maps.SurroundingPlacesFragment;
import com.example.world.game.visibles.creatures.CreaturesFragment;
import com.example.world.game.visibles.items.ItemFragment;
import com.example.world.game.visibles.resources.ResourcesFragment;
import com.example.world.gameActivity.GameActivity;
import com.example.world.gameActivity.MainMenuFragment;
import com.example.world.gameActivity.ViewFragment;

public class Panels {
    public final CreatureStatisticsPanel bodyStatistics;
    public final SurroundingPlacesFragment surroundingPlaces;
    public final ItemFragment visibleItems;
    public final CreaturesFragment visibleCreatures;
    public final ResourcesFragment visibleResources;
    public final BehavioursFragment behaviours;
    // public final JPanel weatherPanel; TODO implement changing of foreground view
    // color
    public final MainMenuFragment mainMenuFragment;
    public final ViewFragment view;
    public final Stats stats;
    GameActivity gameActivity;

    public Panels(Stats stats, GameActivity gameActivity) {
        stats.behaviours.setBehavioursFragment(gameActivity.getGameFragmentContainerID());
        
        this.stats = stats;
        this.gameActivity = gameActivity;
        this.bodyStatistics = new CreatureStatisticsPanel(stats.body);
        this.surroundingPlaces = stats.maps.getSurroundingPlacesPanel(null, gameActivity.getGameFragmentContainerID());
        this.visibleItems = new ItemFragment(gameActivity.getGameFragmentContainerID());
        this.visibleCreatures = new CreaturesFragment(gameActivity.getGameFragmentContainerID());
        this.visibleResources = new ResourcesFragment(gameActivity.getGameFragmentContainerID());
        this.view = new ViewFragment();
        // this.weatherPanel = stats.maps.weather.getPanel();
        this.behaviours = stats.behaviours.getPanel();
        this.mainMenuFragment = new MainMenuFragment(gameActivity);
    }
}
