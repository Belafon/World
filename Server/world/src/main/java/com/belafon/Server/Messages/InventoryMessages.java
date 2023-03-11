package com.belafon.Server.Messages;

import com.belafon.Game.Items.Types.*;
import com.belafon.Game.Items.Item;
public abstract class InventoryMessages {

    public abstract void ClothesPutOn(Clothes clothes);
    public abstract void ClothesPutOff(Clothes clothes);
    public abstract void setAddItem(Item item);
	public abstract void setRemoveItem(Item item);
}
