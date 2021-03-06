package Game.Items.TypeItem;

import Game.Items.ListOfAllItemTypes.NamesOfClothesItemTypes;
public class ClothesTypeItem extends TypeItem{
    public enum parts_of_body{
		head, shoulders, arms, hands, legs, body, feets
	}
    public final int visibilityInfluence; // change of visibility of player
	public final int noise;
	public final int warm;
	public final int defense;
    public final parts_of_body typeOfClothes;
    public final NamesOfClothesItemTypes clothesName;
    public ClothesTypeItem(NamesOfClothesItemTypes name, int weight, int toss, int visibility, String look, int visibilityInfluence, int noise,
            int warm, int defense, parts_of_body typeOfClothes) {
        super(name.name(), weight, toss, visibility, look);
        this.visibilityInfluence = visibilityInfluence;
        this.noise = noise;
        this.warm = warm;
        this.defense = defense;
        this.typeOfClothes = typeOfClothes;
        this.clothesName = name;
    }
}
