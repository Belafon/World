package Game.Items.Types;

import Game.World;
import Game.Calendar.Events.Event;
import Game.Calendar.Events.EventItemChange;
import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import Game.Items.Item;
import Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import Game.Items.TypeItem.FoodTypeItem;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.ItemsMemory.FoodItemsMemory;

import java.util.ArrayList;

public class Food extends Item{
	private volatile int freshness;
    private volatile int warm;
    public ArrayList<SpecialFoodsProperties> specialProperties;
    EventItemChange changeFreshness;
    EventItemChange changeWarm;
    public final FoodItemsMemory memory = new FoodItemsMemory();
    public static final BehavioursPossibleRequirement REQUIREMENT = new BehavioursPossibleRequirement(){};
	public Food(World game, int warm, int freshness, SpecialFoodsProperties[] specialProperties,  FoodTypeItem type) {
        super(game, type);
        this.setFreshness(freshness, game);
        this.setWarm(warm, game);
        this.specialProperties = new ArrayList<SpecialFoodsProperties>();
        for(SpecialFoodsProperties s : specialProperties) this.specialProperties.add(s);
    }
    public int getWarm() {
        return warm;
    }
    public void setWarm(int warm, World game) {
        if (warm < 0)
            warm = 0;

        memory.warm.add(new ObjectsMemoryCell<Integer>(location.game.time.getTime(), warm));
        this.warm = warm;
        if(warm > 0 && changeWarm == null){
            changeWarm = new EventItemChange(game.time.getTime() + owner.getLocation().getTemperature(), game, this);
            game.calendar.add(changeWarm);
        }else if(warm == 0)changeWarm = null;
    }
    public int getFreshness() {
        return freshness;
    }
    public void setFreshness(int freshness, World game) {
        if (freshness < 0)
            freshness = 0;
        memory.freshness.add(new ObjectsMemoryCell<Integer>(location.game.time.getTime(), freshness));
        this.freshness = freshness;
        if(freshness > 0 && changeFreshness == null){
            changeFreshness = new EventItemChange(game.time.getTime() + getType().SPEED_OF_DECAY, game, this);
            game.calendar.add(changeFreshness);
        }else if(freshness == 0)changeFreshness = null;
    }
    public FoodTypeItem getType() {
        return (FoodTypeItem)type;
    }

    @Override
    public synchronized int changeStats(Event event, World game) { // 0 -> stop it, else add to calendar
        /*if(event == changeFreshness){
            setFreshness(freshness - 1, game);
            if(freshness != 0) return getType().speedOfDecay;
        }else{
            setWarm(warm - 1, game);
            if(warm != 0) return owner.getPosition().getTemperature(); 
        }*/
        return 0;
    }

    @Override
    public BehavioursPossibleRequirement[] getBehavioursPossibleRequirementType() {
        return new BehavioursPossibleRequirement[]{type, REQUIREMENT};
    }
}
