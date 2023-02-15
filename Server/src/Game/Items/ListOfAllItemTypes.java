package Game.Items;

import java.util.Hashtable;
import java.util.Map;

import Game.Items.TypeItem.ToolTypeItem;
import Game.Time.Time;
import Game.Items.TypeItem.ClothesTypeItem;
import Game.Items.TypeItem.FoodTypeItem;
import Game.Items.TypeItem.QuestTypeItem;
import Game.Items.TypeItem.SpaceTypeItem;

public class ListOfAllItemTypes {
    public static void setUpItems(){
        setUpTool();
        setUpFood();
        setUpQuest();
        setUpClothes();
        setUpSpaceItems();
    }


    public static Map<NamesOfSpaceItemTypes, SpaceTypeItem> spaceItems = new Hashtable<>(); 

    public enum NamesOfSpaceItemTypes {
        Bag
    }
    
    private static void setUpSpaceItems() {
        spaceItems.put(NamesOfSpaceItemTypes.Bag, new SpaceTypeItem(NamesOfSpaceItemTypes.Bag, 320, 0, 35, "Huge leather bag", 300)); // speedOfDecay will be 0 after 1 month
    }


    public static Map<NamesOfFoodItemTypes, FoodTypeItem> foodTypes = new Hashtable<>(); 
    public enum NamesOfFoodItemTypes{
        Apple
    }

    private static void setUpFood(){
        foodTypes.put(NamesOfFoodItemTypes.Apple, new FoodTypeItem(NamesOfFoodItemTypes.Apple, 120, 1, 15, "Rounded apple", 25, Time.monthsToTicks(1) / 100)); // speedOfDecay will be 0 after 1 month
    }


    public static Map<NamesOfClothesItemTypes, ClothesTypeItem> clothesTypes = new Hashtable<>(); 
    public enum NamesOfClothesItemTypes{
    }
    private static void setUpClothes(){
        
    }


    public static Map<NamesOfQuestItemTypes, QuestTypeItem> QuestTypes = new Hashtable<>(); 
    public enum NamesOfQuestItemTypes{
    }
    private static void setUpQuest(){
        
    }



    public static Map<NamesOfToolItemTypes, ToolTypeItem> ToolTypes = new Hashtable<>(); 
    public enum NamesOfToolItemTypes{
    }
    private static void setUpTool(){
        
    }
}
