package Game.Creatures.Behaviour.Behaviours;

import Game.Game;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Items.Types.Food;

public class Eat extends Behaviour{
    private final Food food;
    public Eat(Game game, Creature creature, Food food) {
        super(game, 0, 0, creature, null);
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
    
}
