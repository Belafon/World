package com.belafon.Server.SendMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.belafon.Console.ConsolePrint;
import com.belafon.Server.Client;
import com.belafon.Server.Messages.PlayerMessages.BehavioursPlayersMessages;
import com.belafon.Server.Messages.PlayerMessages.ConditionPlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.CreatureVisiblePlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.InventoryPlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.ItemVisiblePlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.ResourceVisiblePlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.ServerPlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.SurroundingPlayerMessages;

/**
 * Devides messages from server to concrete client 
 * to meaningful groups.
 */
public class PlayersMessageSender {
    public PrintWriter output;
	public final Client client;
	public volatile Socket clientSocket;
	public final ServerPlayerMessages server = new ServerPlayerMessages(this);
    public final SurroundingPlayerMessages surrounding = new SurroundingPlayerMessages(this);
    public final CreatureVisiblePlayerMessages creatureVisibles = new CreatureVisiblePlayerMessages(this);
    public final ItemVisiblePlayerMessages itemVisibles = new ItemVisiblePlayerMessages(this);
    public final ResourceVisiblePlayerMessages resoruceVisibles = new ResourceVisiblePlayerMessages(this);
	public final ConditionPlayerMessages condition = new ConditionPlayerMessages(this);
	public final InventoryPlayerMessages inventory = new InventoryPlayerMessages(this);
    public final BehavioursPlayersMessages behaviour = new BehavioursPlayersMessages(this);
    public final MessageSender sender;

    public PlayersMessageSender(Socket clientSocket, Client client) {
        sender = MessageSender.getBuilder()
                .setServer(server)
                .setBehavioursMessages(behaviour)
                .setCondition(condition)
                .setInventory(inventory)
                .setSurrounding(surrounding)
                .setItemVIsible(itemVisibles)
                .setCreatureVisible(creatureVisibles)
                .setResourceVisible(resoruceVisibles)
                .build();
        this.clientSocket = clientSocket;
        this.client = client;
        try {
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        } catch (IOException e) {
            ConsolePrint.error("SendMessage: Message ERROR in players connection :: " + client.name + " :: ->\n " + e);
            e.printStackTrace();
        }
    }
	
    /**
     * Sends message to the client.
     */
	public synchronized void sendLetter(String string) {
		ConsolePrint.message_to_player(string, client.name);
		output.println(string); // blank line between headers and content, very important !
		output.flush(); // flush character output stream buffer
	}
}
