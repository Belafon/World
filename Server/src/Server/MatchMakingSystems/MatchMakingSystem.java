package Server.MatchMakingSystems;

import Server.Client;
import Server.Server;

public abstract class MatchMakingSystem {
    public int numberOfPlayers; // in queue
    public int numberOfPlayersToStart;
    public Server server;
    //private volatile Game new_game;

    public MatchMakingSystem(Server server, int numberOfPlayersToStart){
        this.numberOfPlayersToStart = numberOfPlayersToStart;
		this.server = server;
		//new_game = new Game(number_of_players, server, sizeOfMap);
    }
    protected abstract void addClient(Client client);
    protected abstract void removeClient(Client client);
    protected synchronized void setNumberOfPlayers(int numberOfPalyers){
        this.numberOfPlayers = numberOfPalyers;
    }

    public enum Condition{
		idle,
		waitingInQueue,
		playing
	}

    public synchronized void changeQueue(boolean b, Client client) {
        if(b) addClient(client);
        else removeClient(client);
    }
}
