package com.belafon.Server.SendMessage;

import com.belafon.Server.Messages.BehavioursMessages;
import com.belafon.Server.Messages.ConditionCreatureMessages;
import com.belafon.Server.Messages.InventoryMessages;
import com.belafon.Server.Messages.ServerMessages;
import com.belafon.Server.Messages.SurroundingMessages;

public abstract class MessageSender {
	public ServerMessages server;
	public SurroundingMessages surrounding;
	public ConditionCreatureMessages condition;
	public InventoryMessages inventory;
    public BehavioursMessages behavioursMessages; 
	protected void setMessageSender(ServerMessages server, SurroundingMessages surrounding, ConditionCreatureMessages condition,
		InventoryMessages inventory, BehavioursMessages behavioursMessages){
		this.server = server;
		this.surrounding = surrounding;
		this.condition = condition;
		this.inventory = inventory;
        this.behavioursMessages = behavioursMessages;
	}
}
