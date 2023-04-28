package com.world.pcclient;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.world.pcclient.client.Client;

public class App {
    public static void main(String[] args) {
        Stats stats = new Stats();
        Panels panels = new Panels(stats);
        try {
            SwingUtilities.invokeAndWait(() -> {
                panels.mainWindow.displayPanels(panels);
                stats.body.setStatsLists();
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
        new Client(panels, stats);
    }
}
