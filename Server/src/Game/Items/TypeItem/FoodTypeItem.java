package Game.Items.TypeItem;

import java.util.ArrayList;

import Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import Game.Items.ListOfAllItemTypes.NamesOfFoodItemTypes;

public class FoodTypeItem extends TypeItem{
    private volatile int filling;
    public final int speedOfDecay; // less is faster
    public SpecialFoodsProperties[] startSpecialProperties;
    public final NamesOfFoodItemTypes foodName;
    public FoodTypeItem(NamesOfFoodItemTypes name, int weight, int toss, int visibility, String look, int filling, int speedOfDecay) {
        super(name.name(), weight, toss, visibility, look);
        this.setFilling(filling);
        this.speedOfDecay = speedOfDecay;
        this.foodName = name;
    }

    public int getFilling() {
        return filling;
    }
    public void setFilling(int filling) {
        this.filling = filling;
    }
    public ArrayList<SpecialFoodsProperties> getStartSpecialFoodProperties(){
        ArrayList<SpecialFoodsProperties> array = new ArrayList<>();
        for(SpecialFoodsProperties s : startSpecialProperties)
            array.add(s);
        return array;
    }
}
