package com.belafon.Game;

import java.util.ArrayList;

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
import com.belafon.Game.Maps.Maps;
import com.belafon.Game.Maps.Resources.ListOfAllTypesOfResources;
import com.belafon.Game.Maps.Resources.Resource;

public class World implements Runnable {
    public volatile boolean isRunning = false;
    public final Server server;
    public final ArrayList<Player> players = new ArrayList<Player>();
    public final ArrayList<Creature> creatures = new ArrayList<Creature>();
    public Time time;
    public final CalendaryLoop loop = new CalendaryLoop(this);
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

        new Thread(loop).start();

        for (Player player : players)
            player.gameStart();

        spawnMashrooms();
        spawnDeer();
        for (int i = 0; i < 10; i++) {
            spawnApple();
        }
    }

    private void spawnDeer() {
        Animal deer = new Animal(this, "deer1", maps.maps[0].places[0][0],
            "Nice brown wealthy deer", 5, AnimalRace.deer);
        maps.maps[0].places[0][0].addCreature(deer);

        for (Player player : players) {
            player.addVisibleObject(deer);
        }
    }

    private void spawnMashrooms() {
        Resource mushrooms = maps.maps[0].places[0][0].addResource(
                ListOfAllTypesOfResources.typesOfResources
                        .get(ListOfAllTypesOfResources.NamesOfTypesOfResources.mushrooms),
                newEventId);

        for (Player player : players) {
            player.addVisibleObject(mushrooms);
        }
    }

    private void spawnApple() {
        Food apple = new Food(this, 0, 0,
                new SpecialFoodsProperties[0],
                ListOfAllItemTypes.foodTypes.get(ListOfAllItemTypes.NamesOfFoodItemTypes.apple),
                null);

        maps.maps[0].places[0][0].addItem(apple);

        for (Player player : players) {
            player.addVisibleObject(apple);
        }
    }

    private int newEventId = 0;

    public int getNewEventId() {
        return newEventId++;
    }

    private int nextCreatureId = 0;

    public synchronized int getCreatureId() {
        return nextCreatureId++;
    }

    private int nextItemId = 0;

    public synchronized int ItemId() {
        return nextItemId++;
    }

    private World() {
        this.server = null;
        time = new Time(this, Server.clocks, dailyLoop);
    }

    public static World testingWorld() {
        return new World();
    }
}
