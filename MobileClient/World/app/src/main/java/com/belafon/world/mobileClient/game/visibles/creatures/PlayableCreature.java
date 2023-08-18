package com.belafon.world.mobileClient.game.visibles.creatures;

import java.util.HashSet;
import java.util.Set;

import com.belafon.world.mobileClient.game.Stats;
import com.belafon.world.mobileClient.game.behaviours.BehavioursRequirement;
import com.belafon.world.mobileClient.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public class PlayableCreature extends Creature {
    public static final Set<BehavioursPossibleIngredient> allIngredients = new HashSet<>();
    public final Stats stats = new Stats();

    public PlayableCreature(String name, int id, String look, Set<BehavioursRequirement> requirements) {
        super(name, id, look, requirements);
    }
}
