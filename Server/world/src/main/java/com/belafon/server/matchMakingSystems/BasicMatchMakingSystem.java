package com.belafon.server.matchMakingSystems;

import com.belafon.server.Client;
import com.belafon.server.Server;
import com.belafon.world.World;
import com.belafon.world.creatures.Player;

/**
 * This dumb matchmaking system just waits until there is at least 
 * the minimum clients in the queue for start of new game. 
 */
public class BasicMatchMakingSystem extends MatchMakingSystem {
    // the object of game is already created
	// it waits to all players
    private volatile World game;
    
    /**
     * The number of players which are needed to start the game
     */
    public volatile int numberOfPlayersToStart;

    public BasicMatchMakingSystem(Server server, int numberOfPlayersForStartTheGame) {
        this.numberOfPlayersToStart = numberOfPlayersForStartTheGame;
        game = new World(server);
    }

    /**
     * Adds client into the queue, 
     * if there are enough clients, 
     * new game starts.
     * 
     * New Word is created and is waiting for 
     * next clients.
     * 
     * The status of updated queue is sent
     * to the queues. 
     */
    @Override
    public synchronized void addClient(Client client) {
        if(client.queueCondition != Condition.idle) return; 
        client.player = new Player(client, game, "name", game.maps.maps[0].places[0][0], "");
        game.players.add(client.player);
        game.creatures.add(client.player);
        client.actualGame = game;
        if(numberOfPlayersToStart > numberOfPlayers + 1) setNumberOfPlayers(numberOfPlayers + 1);
		else { // lets start the game...
            setNumberOfPlayers(numberOfPlayersToStart);
            new Thread(game).start();
            game = new World(client.getServer());
            setNumberOfPlayers(0);
		}
    }

    @Override
    public synchronized void removeClient(Client client) {
        if(client.queueCondition != Condition.waitingInQueue) return; 
        if(game.players.remove(client.player)) {
			client.player = null;
			client.actualGame = null;
			setNumberOfPlayers(numberOfPlayers - 1);
		}
    }
    
    @Override
    public synchronized void setNumberOfPlayers(int numberOfPalyers){
        this.numberOfPlayers = numberOfPalyers;
        synchronized(game.players){
            for(Player player : game.players)
                player.client.writer.server.setNumberOfPlayersInQueue(numberOfPlayersToStart - numberOfPalyers);
        }

    }
}
