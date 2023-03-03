package Game.Creatures.Behaviour.Behaviours;

import java.util.Map;

import Game.World;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Creatures.Behaviour.BehaviourType;
import Game.Creatures.Behaviour.BehaviourTypeBuilder;
import Game.Items.ListOfAllItemTypes;
import Game.Items.Types.Food;

public class Eat extends Behaviour{
    private final Food food;

    public static final BehaviourType type; 
    static {
        type = new BehaviourTypeBuilder(Eat.class)
            .addConsumableRequirement(ListOfAllItemTypes.foodTypes.get(Food.REQUIREMENT), 1)
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
    public Map<BehavioursPossibleRequirement, Integer> getRequirements() {
        return type.requirements;
    }
    
    @Override
    public Map<ConsumableBehavioursPossibleRequirement, Integer> getConsumableRequirements() {
        return type.consumableRequirements;
    }

    @Override
    public BehaviourType getType() {
        return type;
    }
    
}
