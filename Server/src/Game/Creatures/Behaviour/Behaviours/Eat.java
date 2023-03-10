package Game.Creatures.Behaviour.Behaviours;

import java.util.Map;

import Game.World;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Creatures.Behaviour.BehaviourType;
import Game.Creatures.Behaviour.BehaviourTypeBuilder;
import Game.Creatures.Behaviour.BehaviourType.IngredientsCounts;
import Game.Items.ListOfAllItemTypes;
import Game.Items.Types.Food;

public class Eat extends Behaviour{
    private final Food food;

    public static final BehaviourType type; 
    static {
        type = new BehaviourTypeBuilder(Eat.class)
            .addRequirement(ListOfAllItemTypes.foodTypes.get(Food.REQUIREMENT), new IngredientsCounts(1, 0))
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
