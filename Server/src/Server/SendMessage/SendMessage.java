package Server.SendMessage;

import Server.Messages.BehavioursMessages;
import Server.Messages.ConditionCreatureMessages;
import Server.Messages.InventoryMessages;
import Server.Messages.ServerMessages;
import Server.Messages.SurroundingMessages;

public abstract class SendMessage {
	public ServerMessages server;
	public SurroundingMessages surrounding;
	public ConditionCreatureMessages condition;
	public InventoryMessages inventory;
    public BehavioursMessages behavioursMessages; 
	protected void setSendMessage(ServerMessages server, SurroundingMessages surrounding, ConditionCreatureMessages condition,
		InventoryMessages inventory, BehavioursMessages behavioursMessages){
		this.server = server;
		this.surrounding = surrounding;
		this.condition = condition;
		this.inventory = inventory;
        this.behavioursMessages = behavioursMessages;
	}
}
