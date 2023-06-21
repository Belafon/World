package com.world.pcclient;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import com.world.pcclient.client.Client;
import com.world.pcclient.mainWindow.MainWindow;
import com.world.pcclient.visibles.creatures.PlayableCreature;

public class App {
    private static PlayableCreature currenCreature = null;
    private static Set<PlayableCreature> playableCreatures = new HashSet<>();
    private static Stats stats;
    public static Client client;
    public static void main(String[] args) {
        stats = new Stats();
        Panels panels = new Panels(stats);
        try {
            SwingUtilities.invokeAndWait(() -> {
                panels.mainWindow = new MainWindow(Panels.PREFERED_SCREEN);
                panels.mainWindow.displayPanels(panels);
                stats.body.setStatsLists();
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
        client = new Client(panels, stats);
    }
    
    public static void addNewPlayableCreature(PlayableCreature creature){
        playableCreatures.add(creature);
        if(currenCreature == null)
            setCurrentPlayableCreature(creature);
    }

    public static void setCurrentPlayableCreature(PlayableCreature creature) {
        currenCreature = creature;
        //stats = creature.stats;
    }

    public static PlayableCreature getCurrentPlayableCreature() {
        return currenCreature;
    }
}
