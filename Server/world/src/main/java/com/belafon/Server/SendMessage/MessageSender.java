package com.belafon.Server.SendMessage;

import com.belafon.Server.Messages.BehavioursMessages;
import com.belafon.Server.Messages.ConditionCreatureMessages;
import com.belafon.Server.Messages.CreatureVisibleMessages;
import com.belafon.Server.Messages.InventoryMessages;
import com.belafon.Server.Messages.ItemVisibleMessages;
import com.belafon.Server.Messages.ResourceVisibleMessages;
import com.belafon.Server.Messages.ServerMessages;
import com.belafon.Server.Messages.SurroundingMessages;

public final class MessageSender {
    private MessageSender(){}
    public ServerMessages server;
    public SurroundingMessages surrounding;
    public ConditionCreatureMessages condition;
    public InventoryMessages inventory;
    public BehavioursMessages behavioursMessages;
    public ItemVisibleMessages itemVIsible;
    public ResourceVisibleMessages resourceVisible;
    public CreatureVisibleMessages creatureVisible;

    public static MessageSenderBuilder getBuilder() {
        MessageSender sender = new MessageSender();
        return sender.new MessageSenderBuilder();
    }
    public final class MessageSenderBuilder {
        public MessageSenderBuilder setServer(ServerMessages server) {
                MessageSender.this.server = server;
                return this;
            }
            public MessageSenderBuilder setSurrounding(SurroundingMessages surrounding) {
                MessageSender.this.surrounding = surrounding;
                return this;
            }
            public MessageSenderBuilder setCondition(ConditionCreatureMessages condition) {
                MessageSender.this.condition = condition;
                return this;
            }
            public MessageSenderBuilder setInventory(InventoryMessages inventory) {
                MessageSender.this.inventory = inventory;
                return this;
            }
            public MessageSenderBuilder setBehavioursMessages(BehavioursMessages behavioursMessages) {
                MessageSender.this.behavioursMessages = behavioursMessages;
                return this;
            }
            public MessageSenderBuilder setItemVIsible(ItemVisibleMessages itemVIsible) {
                MessageSender.this.itemVIsible = itemVIsible;
                return this;
            }
            public MessageSenderBuilder setResourceVisible(ResourceVisibleMessages resourceVisible) {
                MessageSender.this.resourceVisible = resourceVisible;
                return this;
            }

            public MessageSenderBuilder setCreatureVisible(CreatureVisibleMessages creatureVisible) {
                MessageSender.this.creatureVisible = creatureVisible;
                return this;
            }
            public MessageSender build(){
                if(server == null || surrounding == null || condition == null 
                    || inventory == null || behavioursMessages == null
                    || itemVIsible == null || resourceVisible == null || creatureVisible == null)
                    throw new Error("MessageSender: some messages are missing at the end of initialization");
                return MessageSender.this;
            }
    }
}
