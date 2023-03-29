package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Server.Messages.ItemVisibleMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;

public class ItemVisiblePlayerMessages implements ItemVisibleMessages {
    public final PlayersMessageSender sender;

    public ItemVisiblePlayerMessages(PlayersMessageSender sender) {
        this.sender = sender;
    }   

}
