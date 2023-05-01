package com.belafon.server.sendMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.belafon.console.ConsolePrint;
import com.belafon.server.Client;
import com.belafon.server.messages.playerMessages.BehavioursPlayersMessages;
import com.belafon.server.messages.playerMessages.ConditionPlayerMessages;
import com.belafon.server.messages.playerMessages.CreatureVisiblePlayerMessages;
import com.belafon.server.messages.playerMessages.InventoryPlayerMessages;
import com.belafon.server.messages.playerMessages.ItemVisiblePlayerMessages;
import com.belafon.server.messages.playerMessages.ResourceVisiblePlayerMessages;
import com.belafon.server.messages.playerMessages.ServerPlayerMessages;
import com.belafon.server.messages.playerMessages.SurroundingPlayerMessages;

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
     * Sends message to the client through the server.
     */
	public synchronized void sendLetter(String string) {
		ConsolePrint.message_to_player(string, client.name);
		output.println(string); // blank line between headers and content, very important !
		output.flush(); // flush character output stream buffer
	}
}
