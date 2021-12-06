package Game;

import java.util.ArrayList;

import Game.Creatures.Creature;
import Game.Creatures.Player;
import Game.Calendar.Calendar;
import Server.Server;
import Game.Time.DailyLoop;
import Game.Time.Time;
import Game.Time.CalendaryLoop;
import Game.Maps.Maps;

public class World {
    public volatile boolean isRunning = false;
    public final Server server;
    public GameCondition condition = GameCondition.preparing;
    public final ArrayList<Player> players = new ArrayList<Player>();
    public final ArrayList<Creature> creatures = new ArrayList<Creature>();
    public Time time;
    public final CalendaryLoop loop = new CalendaryLoop(this);
    public final Calendar calendar = new Calendar(this);
    public final DailyLoop dailyLoop = new DailyLoop(this);
    public final Maps maps = new Maps(this);
    public static enum GameCondition{
		preparing,
		running,
		hasEnded
	}

    public World(Server server) {
		this.server = server;
        server.games.add(this);
        // start();
	}

    public void start(){
        for(Player player : players)player.client.writer.server.startGame();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time = new Time(this, server.clocks, dailyLoop);
        isRunning = true;
        Thread loopThread = new Thread(loop);
		loopThread.start();

        for(Player player : players)player.gameStart();
    }

    private int newEventId = 0;
    public int getNewEventId(){
        return newEventId++;
    }
    private int nextCreatureId = 0;
    public synchronized int getCreatureId(){
        return nextCreatureId++;
    }

    private int nextItemId = 0;
    public synchronized int ItemId() {
        return nextItemId++;
    }
}
