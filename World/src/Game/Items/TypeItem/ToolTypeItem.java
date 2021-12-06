package Game.Items.TypeItem;

import Game.Items.ListOfAllItemTypes.NamesOfToolItemTypes;

public class ToolTypeItem extends TypeItem{
    public final int rateOfDecay;
    public final NamesOfToolItemTypes toolName;
    public ToolTypeItem(NamesOfToolItemTypes name, int weight, int toss, int visibility, String look, int rateOfDecay) {
        super(name.name(), weight, toss, visibility, look);
        this.rateOfDecay = rateOfDecay;
        this.toolName = name;
    }
}
