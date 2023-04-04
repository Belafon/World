package com.example;

import com.example.bodyStats.CreatureStatisticsPanel;
import com.example.client.chatListener.ChatListenerPanel;
import com.example.mainWindow.MainWindow;
import com.example.maps.SurroundingPlacesPanel;

public class Panels {
    private static final int MAIN_SCREEN = 0;
    //private static final int SECONDARY_SCREEN = 2;
    private static final int PREFERED_SCREEN = MAIN_SCREEN;

    public final MainWindow mainWindow = new MainWindow(PREFERED_SCREEN);
    public final CreatureStatisticsPanel bodyStatistics;
    public final ChatListenerPanel listenerPanel;
    public final SurroundingPlacesPanel surroundingPlaces;

    public Panels(Stats stats) {
        this.bodyStatistics = new CreatureStatisticsPanel(stats.body);
        this.listenerPanel = new ChatListenerPanel();
        this.surroundingPlaces = stats.maps.getSurroundingPlacesPanel();
    }
}
