package com.world.pcclient.visibles.creatures;

import java.util.HashSet;
import java.util.Set;

import com.world.pcclient.Stats;
import com.world.pcclient.behaviours.Behaviour;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public class PlayableCreature extends Creature {
    public static final Set<BehavioursPossibleIngredient> allIngredients = new HashSet<>();
    public final Stats stats = new Stats();

    public PlayableCreature(String name, int id, String look, Set<Behaviour> behaviours) {
        super(name, id, look, behaviours);
    }
}
