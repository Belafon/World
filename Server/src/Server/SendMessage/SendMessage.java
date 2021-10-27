package Server.SendMessage;

import Server.Messages.ConditionCreatureMessages;
import Server.Messages.InventoryMessages;
import Server.Messages.ServerMessages;
import Server.Messages.SurroundingMessages;

public abstract class SendMessage {
	public ServerMessages server;
	public SurroundingMessages surrounding;
	public ConditionCreatureMessages condition;
	public InventoryMessages inventory;
	protected void setSendMessage(ServerMessages server, SurroundingMessages surrounding, ConditionCreatureMessages condition,
		InventoryMessages inventory){
		this.server = server;
		this.surrounding = surrounding;
		this.condition = condition;
		this.inventory = inventory;
	}
}
