package com.example;

import javax.swing.JPanel;

import com.example.bodyStats.CreatureStatisticsPanel;
import com.example.client.chatListener.ChatListenerPanel;
import com.example.mainWindow.MainWindow;
import com.example.maps.SurroundingPlacesPanel;
import com.example.visibles.creatures.CreaturesPanel;
import com.example.visibles.items.ItemsPanel;
import com.example.visibles.resources.ResourcesPanel;

public class Panels {
    private static final int MAIN_SCREEN = 0;
    // private static final int SECONDARY_SCREEN = 2;
    private static final int PREFERED_SCREEN = MAIN_SCREEN;

    public final MainWindow mainWindow = new MainWindow(PREFERED_SCREEN);
    public final CreatureStatisticsPanel bodyStatistics;
    public final ChatListenerPanel listenerPanel;
    public final SurroundingPlacesPanel surroundingPlaces;
    public final ItemsPanel visibleItems;
    public final CreaturesPanel visibleCreatures;
    public final ResourcesPanel visibleResources;
    public final JPanel weatherPanel;

    public Panels(Stats stats) {
        this.bodyStatistics = new CreatureStatisticsPanel(stats.body);
        this.listenerPanel = new ChatListenerPanel();
        this.surroundingPlaces = stats.maps.getSurroundingPlacesPanel();
        this.visibleItems = new ItemsPanel();
        this.visibleCreatures = new CreaturesPanel();
        this.visibleResources = new ResourcesPanel();
        this.weatherPanel = stats.maps.weather.getPanel();
    }
}
