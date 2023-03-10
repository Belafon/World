package Server.Messages.PlayerMessages;

import Server.Messages.InventoryMessages;
import Server.SendMessage.PlayersMessageSender;
import Game.Items.Types.*;
import Game.Items.Item;
import Game.Items.TypeItem.FoodTypeItem;

public class InventoryPlayerMessages extends InventoryMessages{
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
			nextValues += ((Food)item).getFreshness() + " " + ((FoodTypeItem)item.type).getFilling() + " " + ((Food)item).getWarm();
		}else if(item instanceof Tool){
			nextValues += ((Tool)item).getQuality();
		}else if(item instanceof Clothes){

		}else if(item instanceof QuestItem){

		}
		sendMessage.sendLetter("item addItem " + item.id + " " + item.type.name + " " + item.type.regularWeight + " " + item.type.visibility + " " + item.type.toss + " " + nextValues);
    }
	@Override
	public void setRemoveItem(Item item) {
		sendMessage.sendLetter("item removeItem " + item.id + " " + item.type.name);
    }
}
