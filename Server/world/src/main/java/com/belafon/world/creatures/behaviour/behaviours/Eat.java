package com.belafon.world.creatures.behaviour.behaviours;

import java.util.Map;

import com.belafon.world.World;
import com.belafon.world.creatures.Creature;
import com.belafon.world.creatures.behaviour.Behaviour;
import com.belafon.world.creatures.behaviour.BehaviourType;
import com.belafon.world.creatures.behaviour.BehaviourTypeBuilder;
import com.belafon.world.creatures.behaviour.BehaviourType.IngredientsCounts;
import com.belafon.world.items.types.Food;

public class Eat extends Behaviour{
    private final Food food;

    public static final BehaviourType type; 
    static {
        type = new BehaviourTypeBuilder(Eat.class)
            .addRequirement(Food.REQUIREMENT, new IngredientsCounts(1, 0))
            .build();
    }

    public Eat(World game, Creature creature, Food food) {
        super(game, 0, 0, creature);
        this.food = food;
    }

    @Override
    public void execute() {
        creature.actualCondition.setHunger(creature.actualCondition.getHunger() - food.getType().getFilling());
        creature.actualCondition.setHeat(creature.actualCondition.getHeat() + food.getWarm());
        creature.inventory.removeItem(food);
    }

    @Override
    public void interrupt() {
    }

    @Override
    public void cease() {
    }

    @Override
    public String canCreatureDoThis() {

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
