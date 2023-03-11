package Server.MatchMakingSystems;

import Server.Client;
import Server.Server;
import Game.World;
import Game.Creatures.Player;

public class BasicMatchMakingSystem extends MatchMakingSystem {
    // the object of game is already created
	// it waits to all players
	private volatile World game;
    public BasicMatchMakingSystem(Server server, int numberOfPlayersForStartTheGame){
        super(server, numberOfPlayersForStartTheGame);
        game = new World(server);
    }

    @Override
    public synchronized void addClient(Client client) {
        if(client.queueCondition != Condition.idle) return; 
        client.player = new Player(client, game, "name", game.maps.maps[0].places[0][0], game.getCreatureId(), "");
        game.players.add(client.player);
        client.actualGame = game;
        if(numberOfPlayersToStart > numberOfPlayers + 1) setNumberOfPlayers(numberOfPlayers + 1);
		else { // lets start the game...
            setNumberOfPlayers(numberOfPlayersToStart);
            new Thread(game).start();
            game = new World(server);
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
		for(Player player : game.players)
				player.client.writer.server.setNumberOfPlayersInQueue(numberOfPlayersToStart - numberOfPalyers);

    }
}
