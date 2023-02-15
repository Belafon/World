package Game.Items.TypeItem;

import Game.Items.ListOfAllItemTypes.NamesOfSpaceItemTypes;

public class SpaceTypeItem extends TypeItem {
    public final int volume;

    public SpaceTypeItem(NamesOfSpaceItemTypes name, int weight, int toss, int visibility, String look, int volume) {
        super(name.name(), weight, toss, visibility, look);
        this.volume = volume;
    }
}
