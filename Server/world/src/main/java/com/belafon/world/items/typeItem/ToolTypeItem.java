package com.belafon.world.items.typeItem;

import com.belafon.world.items.ListOfAllItemTypes.NamesOfToolItemTypes;
import com.belafon.world.items.typeItem.tools.ToolsUtilization;

public class ToolTypeItem extends TypeItem{
    public final int rateOfDecay;
    public final NamesOfToolItemTypes toolName;
    public final ToolsUtilization[] toolsUtilizations;

    public ToolTypeItem(NamesOfToolItemTypes name, int weight, int toss, int visibility, String look, int rateOfDecay, ToolsUtilization[] toolsUtilizations) {
        super(name.name(), weight, toss, visibility, look);
        this.rateOfDecay = rateOfDecay;
        this.toolName = name;
        this.toolsUtilizations = toolsUtilizations;
    }
}
