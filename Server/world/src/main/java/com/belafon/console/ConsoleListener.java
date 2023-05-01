package com.belafon.console;

import java.util.Scanner;

import com.belafon.server.Server;
import com.belafon.world.World;
import com.belafon.world.maps.Map;
import com.belafon.world.visibles.creatures.Player;
import com.belafon.world.visibles.creatures.behaviour.behaviours.Move;
import com.belafon.world.visibles.resources.ListOfAllTypesOfResources;
import com.belafon.world.visibles.resources.ListOfAllTypesOfResources.NamesOfTypesOfResources;

/**
 * Handles console inputs from server provider.
 * It is used primarry for debuging.
 * 
 * Messages with ~log are used to log some info about the
 * first created game.
 * 
 * Other messages are automatically send to all clients
 * in the game.
 */
public class ConsoleListener implements Runnable {
    private Server server;

    public ConsoleListener(Server server) {
        this.server = server;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Thread.currentThread().setName("ConsoleListener");
        ConsolePrint.serverInfo("Listener is waiting for text in console input...");
        Scanner sc = new Scanner(System.in);
        while (server.isServerRunning) {
            String message = "";
            try {
                while (sc.hasNextLine()) {
                    message = sc.nextLine().toString();
                    if (!message.equals(""))
                        if (message.split(" ")[0].equals("log")) {
                            try {
                                show_log(message.split(" "));
                            } catch (Exception e) {
                                ConsolePrint.error_small("Wrong log input!");
                            }
                        } else {
                            for (Player player : server.games.get(0).players)
                                player.client.writer.sendLetter(message);
                        }
                }
                Thread.sleep(500);
            } catch (Exception e) {
                sc.close();
                e.printStackTrace();
                break;
            }
        }
        sc.close();
    }

    private void show_log(String[] message) {
        if (message.length < 2)
            return;
        String log = "LOG --> " + message[1] + " --> ";
        if (server.games.size() != 0)
            switch (message[1]) {
                case "date":
                    log += server.games.get(0).time.logDate();
                    break;
                case "clouds":
                    if (message.length >= 3 && message[2].equals("loop"))
                        server.games.get(0).maps.maps[0].sky.printCloudsInLoop = true;
                    else if (message.length >= 3 && message[2].equals("stop"))
                        server.games.get(0).maps.maps[0].sky.printCloudsInLoop = false;
                    else
                        log += server.games.get(0).maps.maps[0].sky.printClouds();
                    break;
                case "weather":
                    if (message.length >= 3 && message[2].equals("loop"))
                        server.games.get(0).maps.maps[0].sky.printWeatherInLoop = true;
                    else if (message.length >= 3 && message[2].equals("stop"))
                        server.games.get(0).maps.maps[0].sky.printWeatherInLoop = false;
                    else
                        log += server.games.get(0).maps.maps[0].sky.printWeathers();
                    break;
                case "wind":
                    log += server.games.get(0).maps.maps[0].sky.strengthOfWind;
                    break;
                case "windDirection":
                    if (message.length >= 3) {
                        int direction = Integer.parseInt(message[2]);
                        if (direction < 0 || direction > 7)
                            return;
                        server.games.get(0).maps.maps[0].sky.directionOfWind = direction;
                        log += "done";
                    } else
                        log += server.games.get(0).maps.maps[0].sky.directionOfWind;
                    break;
                case "health":
                    log += server.games.get(0).players.get(0).abilityCondition.getHealth();
                    break;
                case "move":
                    if (message.length == 4) {
                        server.games.get(0).players.get(0)
                                .setBehaviour(new Move(server.games.get(0), server.games.get(0).players.get(0),
                                        server.games.get(0).maps.maps[0].places[Integer.parseInt(message[2])][Integer
                                                .parseInt(message[3])]));
                    }
                    break;
                case "calendar":
                    if (message.length >= 3 && message[2].equals("loop"))
                        server.games.get(0).calendarsLoop.logLoopThread = true;
                    else if (message.length >= 3 && message[2].equals("stop"))
                        server.games.get(0).calendarsLoop.logLoopThread = false;
                    break;
                case "map":
                    if (message.length >= 3)
                        log = server.games.get(0).maps.maps[Integer.valueOf(message[2])].logMap();
                    else
                        log = server.games.get(0).maps.maps[0].logMap();
                case "temp":
                    log += "\n" + server.games.get(0).maps.maps[0].logTemperature();
                    break;
                case "addResource": // log addResource x y name amount log addResource 0 0 treeSpruce 100
                    if (message.length > 5)
                        server.games.get(0).maps.maps[0].places[Integer.parseInt(message[2])][Integer
                                .parseInt(message[3])]
                                .addResource(
                                        ListOfAllTypesOfResources.typesOfResources
                                                .get(NamesOfTypesOfResources.valueOf(message[4])),
                                        Integer.parseInt(message[5]));
                    break;
                case "place":
                    if (message.length >= 3)
                        server.games.get(0).maps.maps[0].places[Integer.parseInt(message[2])][Integer
                                .parseInt(message[3])].log();
                    break;
                default:
                    return;
            }
        ConsolePrint.success("ConsoleListener", log);
    }

}