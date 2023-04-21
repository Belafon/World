package com.belafon.server;


import java.net.Socket;

import com.belafon.server.matchMakingSystems.MatchMakingSystem;
import com.belafon.server.sendMessage.PlayersMessageSender;
import com.belafon.world.World;
import com.belafon.world.creatures.Player;

public class Client {
	// object which is used to send new messages to the client
    public String name;
    // this is special object responsible for sending messages to the client
	public volatile PlayersMessageSender writer;
	public volatile World actualGame;
	private volatile boolean disconnected = false;
    public volatile Player player;
	public final String ipAddress;
	public volatile MatchMakingSystem.Condition queueCondition = MatchMakingSystem.Condition.idle;
    private Server server;
    
    public Client(Socket clientSocket, Server server) {
        this.server = server;
        bindNewSendMessage(clientSocket);
        ipAddress = clientSocket.getInetAddress().getHostAddress().toString();
    }
	
    /** this will try to reconnect the player to 
     * his last state.
     */
	public void reconnect() {
		if(actualGame == null) { // if the game has ended, there is no reason to continue
			// TODO Lets get result of the game
			return;
		}
		// TODO Lets connect player to the game
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

    /** 
     * This disconnect the player from his current game
     * It is called for example, when the client sends 
     * concrete message.
     * 
     * It is not called, when the connection between 
     * client and the server is interrupted.
     */
    public void disconnect() {
        if(!disconnected){
            disconnected = true;
            actualGame.disconnectPlayer();
        }
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setInitDisconnectedState(boolean state) {
        disconnected = state;
    }
}
