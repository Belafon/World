package com.belafon.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.belafon.console.ConsolePrint;
import com.belafon.server.Server;
import com.belafon.world.calendar.Calendar;
import com.belafon.world.creatures.Creature;
import com.belafon.world.creatures.Player;
import com.belafon.world.maps.GenerateVisibles;
import com.belafon.world.maps.Maps;
import com.belafon.world.maps.place.Place;
import com.belafon.world.time.CalendaryLoop;
import com.belafon.world.time.DailyLoop;
import com.belafon.world.time.Time;

public class World implements Runnable {
    public volatile boolean isRunning = false;
    public final Server server;
    public final VisibleIDs visibleIds = new VisibleIDs();
    public final List<Player> players = Collections.synchronizedList(new ArrayList<Player>());
    public final List<Creature> creatures = Collections.synchronizedList(new ArrayList<Creature>());
    public Time time;
    public final CalendaryLoop calendarsLoop = new CalendaryLoop(this);
    public final Calendar calendar = new Calendar(this);
    public final DailyLoop dailyLoop = new DailyLoop(this);
    public final Maps maps = new Maps(this);

    public World(Server server) {
        this.server = server;
        server.games.add(this);
        time = new Time(this, Server.clocks, dailyLoop);
    }

    @Override
    public void run() {
        ConsolePrint.gameInfo("World: new world starts...");
        for (Player player : players)
            player.client.writer.server.startGame();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isRunning = true;

        new Thread(calendarsLoop).start();

        for (Player player : players)
            player.gameStart();

        Place cornerPlace = maps.maps[0].places[0][0];
        GenerateVisibles.spawnMashroomsAndAllCreaturesNotices(cornerPlace, this);
        GenerateVisibles.spawnDeerAndAllCreaturesNotices(cornerPlace, this);
        for (int i = 0; i < 10; i++) {
            GenerateVisibles.spawnAppleAndAllCreaturesNotices(cornerPlace, this);
        }
    }

    private World() {
        this.server = null;
        time = new Time(this, Server.clocks, dailyLoop);
    }

    /**
     * builder method for testing
     */
    public static World testingWorld() {
        return new World();
    }

    /**
     * Disconnects player from the game
     */
    public void disconnectPlayer() {
        synchronized (players) {
            for (Player player : players) {
                if (!player.isDisconnected())
                    return;
            }
        }

        endTheWorld();
    }

    /**
     * This ends the world. The calendar loop will stop.
     */
    private void endTheWorld() {
        isRunning = false;
        server.endTheWorld(this);
    }
}