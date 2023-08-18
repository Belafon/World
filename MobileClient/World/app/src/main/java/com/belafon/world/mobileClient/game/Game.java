package com.example.world.game;

import com.example.world.client.Client;
import com.example.world.game.visibles.creatures.PlayableCreature;
import com.example.world.gameActivity.GameActivity;
import java.util.HashSet;
import java.util.Set;


public class Game {
    private PlayableCreature currenCreature = null;
    private Set<PlayableCreature> playableCreatures = new HashSet<>();
    public static final Stats stats = new Stats();
    public final Fragments fragments;
    public final GameActivity gameActivity;

    public Game(GameActivity gameActivity) {
        this.gameActivity = gameActivity;

        fragments = new Fragments(stats, gameActivity);
        Client.setFragments(fragments);
        Client.chatListener.setFragments(fragments);
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
