package Server.MatchMakingSystems;

import Server.Client;
import Server.Server;
import Game.Game;
import Game.Creatures.Player;

public class BasicMatchMakingSystem extends MatchMakingSystem {
    // the object of game is already created
	// it waits to all players
	private volatile Game new_game;
    public BasicMatchMakingSystem(Server server, int numberOfPlayersForStartTheGame){
        super(server, numberOfPlayersForStartTheGame);
        new_game = new Game(server);
    }

    @Override
    public synchronized void addClient(Client client) {
        if(client.queueCondition != Condition.idle) return; 
        client.player = new Player(client, new_game, "name", new_game.maps.maps[0].places[0][0], new_game.getCreatureId(), "");
        new_game.players.add(client.player);
        client.actual_game = new_game;
        if(numberOfPlayersToStart > numberOfPlayers + 1) setNumberOfPlayers(numberOfPlayers + 1);
		else { // lets start the game...
			setNumberOfPlayers(numberOfPlayersToStart);
			new Thread(new Runnable() {
				@Override
				public void run() {
                    Thread.currentThread().setName("StartGame");
					new_game.start();
					new_game = new Game(server);
					setNumberOfPlayers(0);
				}
			}).start();
		}
    }

    @Override
    public synchronized void removeClient(Client client) {
        if(client.queueCondition != Condition.waitingInQueue) return; 
        if(new_game.players.remove(client.player)) {
			client.player = null;
			client.actual_game = null;
			setNumberOfPlayers(numberOfPlayers - 1);
		}
    }
    
    @Override
    public synchronized void setNumberOfPlayers(int numberOfPalyers){
        this.numberOfPlayers = numberOfPalyers;
		for(Player player : new_game.players)
				player.client.writer.server.setNumberOfPlayersInQueue(numberOfPlayersToStart - numberOfPalyers);

    }
}
