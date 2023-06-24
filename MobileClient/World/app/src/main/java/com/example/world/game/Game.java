package com.example.world.game;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import com.example.world.client.Client;
import com.example.world.gameActivity.GameActivity;
import com.example.world.game.mainWindow.MainWindow;
import com.example.world.game.Panels;
import com.example.world.game.visibles.creatures.PlayableCreature;

public class Game {
    private PlayableCreature currenCreature = null;
    private Set<PlayableCreature> playableCreatures = new HashSet<>();
    public final Stats stats;
    public final Panels panels;
    public final GameActivity gameActivity;

    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    
        stats = new Stats();
        panels = new Panels(stats, gameActivity);

        Client.setStats(stats);
        Client.setFragments(panels);
    }

    public void addNewPlayableCreature(PlayableCreature creature) {
        playableCreatures.add(creature);
        if (currenCreature == null)
            setCurrentPlayableCreature(creature);
    }

    public void setCurrentPlayableCreature(PlayableCreature creature) {
        currenCreature = creature;
        // stats = creature.stats;
    }

    public PlayableCreature getCurrentPlayableCreature() {
        return currenCreature;
    }
}
