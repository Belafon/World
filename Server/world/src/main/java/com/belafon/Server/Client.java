package com.belafon.Server;


import java.net.Socket;

import com.belafon.Game.Creatures.Player;
import com.belafon.Game.World;
import com.belafon.Server.MatchMakingSystems.MatchMakingSystem;
import com.belafon.Server.SendMessage.PlayersMessageSender;

public class Client {
	// object which is used to send new messages to the client
	public String name;
	public volatile PlayersMessageSender writer;
	public volatile World actualGame;
	public volatile boolean disconnected = false;
	public volatile Player player;
	public final String ipAddress;
	public volatile MatchMakingSystem.Condition queueCondition = MatchMakingSystem.Condition.idle;
    private Server server;
    
    public Client(Socket clientSocket, Server server) {
        this.server = server;
		bindNewSendMessage(clientSocket); 
		ipAddress = clientSocket.getInetAddress().getHostAddress().toString(); 
	}
	
	// lets reconnect the player
	public void reconnect() {
		if(actualGame == null) { // if the game has ended, there is no reason to contine
			// Lets get result of the game
			return;
		}
		// Lets connect player to the game
	}

    public void bindNewSendMessage(Socket clientSocket) {
        writer = new PlayersMessageSender(clientSocket, this);
    }
    
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
