package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Server.Messages.InventoryMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;
import com.belafon.Game.Items.Types.*;
import com.belafon.Game.Items.Item;
import com.belafon.Game.Items.TypeItem.FoodTypeItem;

public class InventoryPlayerMessages implements InventoryMessages{
    public final PlayersMessageSender sendMessage;

    public InventoryPlayerMessages(PlayersMessageSender sendMessage) {
        this.sendMessage = sendMessage;
    }

	@Override
    public void ClothesPutOn(Clothes clothes) {
		sendMessage.sendLetter("item " + clothes.getClass().getSimpleName() + " putOn " + clothes.id);
	}
	@Override
    public void ClothesPutOff(Clothes clothes) {
		sendMessage.sendLetter("item " + clothes.getClass().getSimpleName() + " putOff " + clothes.id);
    }
	@Override
    public void setAddItem(Item item) {
		String nextValues = "";
		if(item instanceof Food){
            nextValues += ((Food) item).getFreshness() + " " + ((FoodTypeItem) item.type).getFilling() + " "
                    + ((Food) item).getWarm();
		}else if(item instanceof Tool){
			nextValues += ((Tool)item).getQuality();
		}else if(item instanceof Clothes){

		}else if(item instanceof QuestItem){

		}
        sendMessage.sendLetter("item addItem " + item.id + " " + item.type.getClass().getSimpleName() + " "
                + item.type.name + " " + item.type.regularWeight + " "
                + item.type.visibility + " " + item.type.toss + " " + nextValues);
    }
	@Override
	public void setRemoveItem(Item item) {
		sendMessage.sendLetter("item removeItem " + item.id + " " + item.type.name);
    }
}
