package com.belafon.world.items.typeItem;

import com.belafon.world.items.ListOfAllItemTypes.NamesOfQuestItemTypes;
public class QuestTypeItem extends TypeItem{
    public final NamesOfQuestItemTypes questItemName;
    public QuestTypeItem(NamesOfQuestItemTypes name, int weight, int toss, int visibility, String look) {
        super(name.name(), weight, toss, visibility, look);
        this.questItemName = name;
    }
    
}