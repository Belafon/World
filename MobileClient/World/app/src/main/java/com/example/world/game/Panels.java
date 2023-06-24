package com.example.world.game;

import com.example.world.game.behaviours.BehavioursPanel;
import com.example.world.game.bodyStats.CreatureStatisticsPanel;
import com.example.world.game.maps.SurroundingPlacesPanel;
import com.example.world.game.visibles.creatures.CreaturesPanel;
import com.example.world.game.visibles.items.ItemsPanel;
import com.example.world.game.visibles.resources.ResourcesPanel;
import com.example.world.gameActivity.GameActivity;
import com.example.world.gameActivity.MainMenuFragment;
import com.example.world.gameActivity.ViewFragment;

public class Panels {
    public final CreatureStatisticsPanel bodyStatistics;
    public final SurroundingPlacesPanel surroundingPlaces;
    public final ItemsPanel visibleItems;
    public final CreaturesPanel visibleCreatures;
    public final ResourcesPanel visibleResources;
    public final BehavioursPanel behaviours;
    // public final JPanel weatherPanel; TODO implement changing of foreground view color
    public final MainMenuFragment mainMenuFragment;
    public final ViewFragment view;
    public final Stats stats;
    GameActivity gameActivity;

    public Panels(Stats stats, GameActivity gameActivity) {
        this.stats = stats;
        this.gameActivity = gameActivity;
        this.bodyStatistics = new CreatureStatisticsPanel(stats.body);
        this.surroundingPlaces = stats.maps.getSurroundingPlacesPanel();
        this.visibleItems = new ItemsPanel();
        this.visibleCreatures = new CreaturesPanel();
        this.visibleResources = new ResourcesPanel();
        this.view = new ViewFragment();
        //this.weatherPanel = stats.maps.weather.getPanel();
        this.behaviours = stats.behaviours.getPanel();
        this.mainMenuFragment = new MainMenuFragment(gameActivity);
    }
}
