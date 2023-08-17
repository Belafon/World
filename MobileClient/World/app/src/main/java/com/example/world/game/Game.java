package com.example.world.game;

import java.util.HashSet;
import java.util.Set;

import com.example.world.client.Client;
import com.example.world.gameActivity.GameActivity;
import com.example.world.game.visibles.creatures.PlayableCreature;

public class Game {
    private PlayableCreature currenCreature = null;
    private Set<PlayableCreature> playableCreatures = new HashSet<>();
    public static final Stats stats = new Stats();
    public final Panels panels;
    public final GameActivity gameActivity;

    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;

        panels = new Panels(stats, gameActivity);
        Client.setFragments(panels);
        Client.chatListener.setFragments(panels);
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
