package Game.Items;

import java.util.Hashtable;
import Game.Items.TypeItem.ToolTypeItem;
import Game.Time.Time;
import Game.Items.TypeItem.ClothesTypeItem;
import Game.Items.TypeItem.FoodTypeItem;
import Game.Items.TypeItem.QuestTypeItem;

public class ListOfAllItemTypes {
    public static void setUpItems(){
        setUpTool();
        setUpFood();
        setUpQuest();
        setUpClothes();
    }


    public static Hashtable<NamesOfFoodItemTypes, FoodTypeItem> foodTypes = new Hashtable<NamesOfFoodItemTypes, FoodTypeItem>(); 
    public enum NamesOfFoodItemTypes{
        Apple
    }

    private static void setUpFood(){
        foodTypes.put(NamesOfFoodItemTypes.Apple, new FoodTypeItem(NamesOfFoodItemTypes.Apple, 120, 1, 15, "Rounded apple", 25, Time.monthsToTicks(1) / 100)); // speedOfDecay will be 0 after 1 month
    }


    public static Hashtable<NamesOfClothesItemTypes, ClothesTypeItem> clothesTypes = new Hashtable<NamesOfClothesItemTypes, ClothesTypeItem>(); 
    public enum NamesOfClothesItemTypes{
    }
    private static void setUpClothes(){
        
    }


    public static Hashtable<NamesOfQuestItemTypes, QuestTypeItem> QuestTypes = new Hashtable<NamesOfQuestItemTypes, QuestTypeItem>(); 
    public enum NamesOfQuestItemTypes{
    }
    private static void setUpQuest(){
        
    }



    public static Hashtable<NamesOfToolItemTypes, ToolTypeItem> ToolTypes = new Hashtable<NamesOfToolItemTypes, ToolTypeItem>(); 
    public enum NamesOfToolItemTypes{
    }
    private static void setUpTool(){
        
    }
}
