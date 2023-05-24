package com.belafon.world.visibles.creatures.behaviour.behaviours;

import java.util.Map;

import com.belafon.world.World;
import com.belafon.world.visibles.creatures.Creature;
import com.belafon.world.visibles.creatures.behaviour.Behaviour;
import com.belafon.world.visibles.creatures.behaviour.BehaviourType;
import com.belafon.world.visibles.creatures.behaviour.BehaviourTypeBuilder;
import com.belafon.world.visibles.creatures.behaviour.BehaviourType.IngredientsCounts;
import com.belafon.world.visibles.items.Item;

public class PickUpItem extends Behaviour {
    public static final BehaviourType type; 
    static {
        type = new BehaviourTypeBuilder(PickUpItem.class)
                .addRequirement(Item.REQUIREMENT_IS_VISIBLE, new IngredientsCounts(0, 1))
                .build();
    }
    private Item item;
    public PickUpItem(World game, int duration, int bodyStrain, Creature creature, Item item) {
        super(game, duration, bodyStrain, creature);
        this.item = item;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute() {
        creature.inventory.addItem(item);
    }

    @Override
    public String canCreatureDoThis() {
        if (!creature.seesVisibleObject(creature))
            return "do_not_have_required_item";
        return null; 
    }

    @Override
    public Map<BehavioursPossibleRequirement, IngredientsCounts> getRequirements() {
        return type.requirements;
    }

    @Override
    public BehaviourType getType() {
        return type;
    }
    
}
