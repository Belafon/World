package com.belafon.Settings;

import com.belafon.Server.Messages.BehavioursMessages;
import com.belafon.Server.Messages.ConditionCreatureMessages;
import com.belafon.Server.Messages.CreatureVisibleMessages;
import com.belafon.Server.Messages.InventoryMessages;
import com.belafon.Server.Messages.ItemVisibleMessages;
import com.belafon.Server.Messages.ResourceVisibleMessages;
import com.belafon.Server.Messages.ServerMessages;
import com.belafon.Server.Messages.SurroundingMessages;
import com.belafon.Server.SendMessage.MessageSender;

public class TestingMessages
    implements BehavioursMessages, ConditionCreatureMessages,
        InventoryMessages, ItemVisibleMessages,
        ResourceVisibleMessages, SurroundingMessages,
        ServerMessages, CreatureVisibleMessages {
    public static MessageSender createMessageSender() {
        final TestingMessages messages = new TestingMessages();

        MessageSender sender = MessageSender.getBuilder()
            .setBehavioursMessages(messages)
            .setCondition(messages)
            .setCreatureVisible(messages)
            .setItemVIsible(messages)
            .setResourceVisible(messages)
            .setServer(messages)
            .setSurrounding(messages)
            .setInventory(messages)
            .build();
        return sender; 
    }
}
