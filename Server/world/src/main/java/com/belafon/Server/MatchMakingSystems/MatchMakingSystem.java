package com.belafon.Server.MatchMakingSystems;

import com.belafon.Server.Client;
import com.belafon.Server.Server;

public abstract class MatchMakingSystem {
    public volatile int numberOfPlayers; // in queue
    public volatile int numberOfPlayersToStart;
    public Server server;
    //private volatile Game new_game;

    public MatchMakingSystem(Server server, int numberOfPlayersToStart){
        this.numberOfPlayersToStart = numberOfPlayersToStart;
		this.server = server;
		//new_game = new Game(number_of_players, server, sizeOfMap);
    }
    public abstract void addClient(Client client);
    public abstract void removeClient(Client client);
    protected synchronized void setNumberOfPlayers(int numberOfPalyers){
        this.numberOfPlayers = numberOfPalyers;
    }

    public enum Condition{
		idle,
		waitingInQueue,
		playing
	}
}
