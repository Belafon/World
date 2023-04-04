package com.belafon.Game.Items.TypeItem;

import java.util.ArrayList;

import com.belafon.Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import com.belafon.Game.Items.ListOfAllItemTypes.NamesOfFoodItemTypes;

public class FoodTypeItem extends TypeItem{
    private volatile int filling;
    public final long SPEED_OF_DECAY; // less is faster
    public SpecialFoodsProperties[] startSpecialProperties;
    public final NamesOfFoodItemTypes foodName;
    public FoodTypeItem(NamesOfFoodItemTypes name, int weight, int toss, int visibility, String look, int filling, long speedOfDecay) {
        super(name.name(), weight, toss, visibility, look);
        this.setFilling(filling);
        this.SPEED_OF_DECAY = speedOfDecay;
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