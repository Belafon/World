package Server.SendMessage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Console.ConsolePrint;
import Server.Client;
import Server.Messages.PlayerMessages.ConditionPlayerMessages;
import Server.Messages.PlayerMessages.InventoryPlayerMessages;
import Server.Messages.PlayerMessages.ServerPlayerMessages;
import Server.Messages.PlayerMessages.SurroundingPlayerMessages;

public class SendMessagePlayer extends SendMessage{
    public PrintWriter output;
	public Client client;
	public Socket clientSocket;
	public final ServerPlayerMessages server = new ServerPlayerMessages(this);
	public final SurroundingPlayerMessages surrounding = new SurroundingPlayerMessages(this);
	public final ConditionPlayerMessages condition = new ConditionPlayerMessages(this);
	public final InventoryPlayerMessages inventory = new InventoryPlayerMessages(this);
	public SendMessagePlayer(Socket clientSocket, Client client) {
        super.setSendMessage(server, surrounding, condition, inventory);
		this.clientSocket = clientSocket;
		this.client = client;
		try {
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
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
