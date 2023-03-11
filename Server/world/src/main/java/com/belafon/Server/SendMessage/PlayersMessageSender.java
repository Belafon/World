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
import com.belafon.Server.Messages.PlayerMessages.InventoryPlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.ServerPlayerMessages;
import com.belafon.Server.Messages.PlayerMessages.SurroundingPlayerMessages;

public class PlayersMessageSender extends MessageSender {
    public PrintWriter output;
	public Client client;
	public Socket clientSocket;
	public final ServerPlayerMessages server = new ServerPlayerMessages(this);
	public final SurroundingPlayerMessages surrounding = new SurroundingPlayerMessages(this);
	public final ConditionPlayerMessages condition = new ConditionPlayerMessages(this);
	public final InventoryPlayerMessages inventory = new InventoryPlayerMessages(this);
    public final BehavioursPlayersMessages behaviour = new BehavioursPlayersMessages(this);
	public PlayersMessageSender(Socket clientSocket, Client client) {
        super.setMessageSender(server, surrounding, condition, inventory, behaviour);
		this.clientSocket = clientSocket;
		this.client = client;
		try {
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
		} catch (IOException e) {
			ConsolePrint.error("SendMessage: Message ERROR in players connection :: " + client.name + " :: ->\n " + e);
			e.printStackTrace();
		}
	}
	
	public synchronized void sendLetter(String string) {
		ConsolePrint.message_to_player(string, client.name);
		output.println(string); // blank line between headers and content, very important !
		output.flush(); // flush character output stream buffer
	}
}
