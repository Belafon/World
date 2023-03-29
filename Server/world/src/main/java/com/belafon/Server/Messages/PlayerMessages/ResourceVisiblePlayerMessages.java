package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Server.Messages.ResourceVisibleMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;

public class ResourceVisiblePlayerMessages implements ResourceVisibleMessages {
    PlayersMessageSender sender;
    public ResourceVisiblePlayerMessages(PlayersMessageSender sender) {
        this.sender = sender; 
    }
 
}
