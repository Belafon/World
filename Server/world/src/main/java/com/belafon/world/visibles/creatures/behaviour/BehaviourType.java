package com.belafon.world.visibles.creatures.behaviour;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.belafon.App;
import com.belafon.world.visibles.creatures.behaviour.behaviours.BehavioursPossibleRequirement;
import com.belafon.world.visibles.creatures.behaviour.behaviours.Eat;
import com.belafon.world.visibles.creatures.behaviour.behaviours.FindConcreteResource;
import com.belafon.world.visibles.creatures.behaviour.behaviours.Move;
import com.belafon.world.visibles.creatures.behaviour.behaviours.PickUpItem;
import com.belafon.world.visibles.items.Item;

/**
 * Each Behaviour has its own Behaviour Type.
 * It holds info about what is required for
 * behaviours execution
 */
public class BehaviourType {
    public static final Set<BehaviourType> ALL_BEHAVIOUR_TYPES_WITHOUT_REQUIREMENT = ConcurrentHashMap.newKeySet();
    public static final Set<BehaviourType> ALL_BEHAVIOUR_TYPES = ConcurrentHashMap.newKeySet();
    private static final Set<BehavioursPossibleRequirement> ALL_REQUIRMENTES = ConcurrentHashMap.newKeySet();

    public static void setupNewRequirement(BehavioursPossibleRequirement requirement) {
        ALL_REQUIRMENTES.add(requirement);
        
        if (App.server == null)
            return;

        synchronized (ALL_REQUIRMENTES) {
            for (var client : App.server.allClients.values()) {
                if (client.player != null) {
                    client.writer.behaviour.setupPossibleReqirement(requirement);
                }
            }
            
        }
    }

    public static Set<BehavioursPossibleRequirement> getAllRequirmentes() {
        synchronized (ALL_REQUIRMENTES) {
            return ALL_REQUIRMENTES;
        }
    }

    public static void setUpAllBehavioursPossibleRequirements() {
        Eat.type.getClass();
        Move.type.getClass();
        FindConcreteResource.type.getClass();
        PickUpItem.type.getClass();
        Item.REQUIREMENT_IS_IN_INVENTORY.getClass();
        Item.REQUIREMENT_IS_VISIBLE.getClass();
    }

    public record IngredientsCounts(String description, int numOfSpecificIngredients, int numOfGeneralIngredients) {
    }

    public final Map<BehavioursPossibleRequirement, IngredientsCounts> requirements;
    public final Class<? extends Behaviour> behaviourClass;
    public final String idName;
    public final String name;
    public final String description;

    protected BehaviourType(String idName, String name, String description,
            Map<BehavioursPossibleRequirement, IngredientsCounts> requirements,
            Class<? extends Behaviour> behaviourClass) {
        this.idName = idName;
        this.name = name;
        this.description = description;
        this.behaviourClass = behaviourClass;
        this.requirements = Collections.unmodifiableMap(requirements);
    }
}