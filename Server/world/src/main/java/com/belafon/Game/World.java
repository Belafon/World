package com.belafon.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.belafon.Console.ConsolePrint;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Player;
import com.belafon.Game.Creatures.Races.Animals.Animal;
import com.belafon.Game.Creatures.Races.Animals.AnimalRaces.AnimalRace;
import com.belafon.Game.Items.ListOfAllItemTypes;
import com.belafon.Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import com.belafon.Game.Items.Types.Food;
import com.belafon.Game.Calendar.Calendar;
import com.belafon.Server.Server;
import com.belafon.Game.Time.DailyLoop;
import com.belafon.Game.Time.Time;
import com.belafon.Game.Time.CalendaryLoop;
import com.belafon.Game.Maps.GenerateVisibles;
import com.belafon.Game.Maps.Maps;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Game.Maps.Place.UnboundedPlace;
import com.belafon.Game.Maps.Resources.ListOfAllTypesOfResources;
import com.belafon.Game.Maps.Resources.Resource;

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
