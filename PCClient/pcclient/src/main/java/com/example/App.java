package com.example;

import com.example.client.Client;

public class App {
    public static void main(String[] args) {
        Stats stats = new Stats();
        Panels panels = new Panels(stats);
        
        panels.mainWindow.displayPanels(panels);
        stats.body.setStatsLists();

        new Client(panels, stats);
    }

}
