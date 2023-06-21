package com.world.pcclient;

import javax.swing.JPanel;

import com.world.pcclient.behaviours.BehavioursPanel;
import com.world.pcclient.bodyStats.CreatureStatisticsPanel;
import com.world.pcclient.client.chatListener.ChatListenerPanel;
import com.world.pcclient.mainWindow.MainWindow;
import com.world.pcclient.maps.SurroundingPlacesPanel;
import com.world.pcclient.visibles.creatures.CreaturesPanel;
import com.world.pcclient.visibles.items.ItemsPanel;
import com.world.pcclient.visibles.resources.ResourcesPanel;

public class Panels {
    private static final int MAIN_SCREEN = 0;
    // private static final int SECONDARY_SCREEN = 2;
    public static final int PREFERED_SCREEN = MAIN_SCREEN;

    public MainWindow mainWindow;
    public final CreatureStatisticsPanel bodyStatistics;
    public final ChatListenerPanel listenerPanel;
    public final SurroundingPlacesPanel surroundingPlaces;
    public final ItemsPanel visibleItems;
    public final CreaturesPanel visibleCreatures;
    public final ResourcesPanel visibleResources;
    public final BehavioursPanel behaviours;
    public final JPanel weatherPanel;
    public final Stats stats;

    public Panels(Stats stats) {
        this.stats = stats;
        this.bodyStatistics = new CreatureStatisticsPanel(stats.body);
        this.listenerPanel = new ChatListenerPanel();
        this.surroundingPlaces = stats.maps.getSurroundingPlacesPanel();
        this.visibleItems = new ItemsPanel();
        this.visibleCreatures = new CreaturesPanel();
        this.visibleResources = new ResourcesPanel();
        this.weatherPanel = stats.maps.weather.getPanel();
        this.behaviours = stats.behaviours.getPanel();
    }
}
