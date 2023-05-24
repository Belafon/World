package com.belafon.world.visibles.creatures.behaviour;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.visibles.creatures.behaviour.behaviours.Eat;
import com.belafon.world.visibles.creatures.behaviour.behaviours.FindConcreteResource;
import com.belafon.world.visibles.creatures.behaviour.behaviours.Move;
import com.belafon.world.visibles.creatures.behaviour.behaviours.PickUpItem;

/**
 * Each Behaviour has its own Behaviour Type.
 * It holds info about what is required for
 * behaviours execution
 */
public class BehaviourType {
    public static final Set<BehaviourType> ALL_BEHAVIOUR_TYPES_WITHOUT_REQUIREMENT = ConcurrentHashMap.newKeySet();

    public static void setUpAllBehavioursPossibleRequirements() {
        Eat.type.getClass();
        Move.type.getClass();
        FindConcreteResource.type.getClass();
        PickUpItem.type.getClass();
    }

    public record IngredientsCounts(int consumable, int unconsumable) {
    }

    public final Map<BehavioursPossibleRequirement, IngredientsCounts> requirements;
    public final Class<? extends Behaviour> behaviourClass;

    protected BehaviourType(Map<BehavioursPossibleRequirement, IngredientsCounts> requirements,
            Class<? extends Behaviour> behaviourClass) {

        this.behaviourClass = behaviourClass;
        this.requirements = Collections.unmodifiableMap(requirements);
    }
}