package com.belafon.Game;

import java.util.ArrayList;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Player;
import com.belafon.Game.Calendar.Calendar;
import com.belafon.Server.Server;
import com.belafon.Game.Time.DailyLoop;
import com.belafon.Game.Time.Time;
import com.belafon.Game.Time.CalendaryLoop;
import com.belafon.Game.Maps.Maps;

public class World implements Runnable {
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
        time = new Time(this, Server.clocks, dailyLoop);
    }
    
    @Override
    public void run() {
        ConsolePrint.gameInfo("World: new world starts...");
        for(Player player : players)player.client.writer.server.startGame();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isRunning = true;
        
        new Thread(loop).start();

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
