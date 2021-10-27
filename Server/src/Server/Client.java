package Server;

import java.net.Socket;

import Game.Creatures.Player;
import Game.Game;
import Server.MatchMakingSystems.MatchMakingSystem;
import Server.SendMessage.SendMessagePlayer;

public class Client {
	// object which is used to send new messages to the client
	public String name;
	public volatile SendMessagePlayer writer;
	public volatile Game actual_game;
	public volatile boolean disconnected = false;
	public volatile Player player;
	public final String ipAddress;
	public volatile MatchMakingSystem.Condition queueCondition = MatchMakingSystem.Condition.idle;
	
	public Client(Socket clientSocket) {
		bindNewSendMessage(clientSocket); 
		ipAddress = clientSocket.getInetAddress().getHostAddress().toString(); 
	}
	
	// lets reconnect the player
	public void reconnect() {
		if(actual_game == null) { // if the game has ended, there is no reason to contine
			// Lets get result of the game
			return;
		}
		// Lets connect player to the game
	}

	public void bindNewSendMessage(Socket clientSocket) {
		writer = new SendMessagePlayer(clientSocket, this);
	}
}
