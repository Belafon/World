package com.belafon.Server.Messages;

import com.belafon.Game.Items.Types.*;
import com.belafon.Game.Items.Item;
public interface InventoryMessages {

    public default void ClothesPutOn(Clothes clothes) {
	}
    public default void ClothesPutOff(Clothes clothes) {
    }
    public default void setAddItem(Item item) {
    }
	public default void setRemoveItem(Item item) {
    }
}
