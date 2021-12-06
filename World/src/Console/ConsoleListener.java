package Console;

import java.util.Scanner;

import Game.Creatures.Player;
import Game.Creatures.Behaviour.Behaviours.Move;
import Server.Server;

public class ConsoleListener implements Runnable{
    private Server server;
    public ConsoleListener(Server server){
        this.server = server;
    }
    @Override
    public void run() {
        
        ConsolePrint.serverInfo("Listener is waiting for text in console input...");
		Scanner sc = new Scanner(System.in);
        while(server.isServerRunning) {
            String message = "";
            try {
                while(sc.hasNextLine()){
                    message = sc.nextLine().toString();
                    if(!message.equals(""))
                    if(message.split(" ")[0].equals("log")) {
						try{
							show_log(message.split(" "));
						}catch(Exception e){
							ConsolePrint.error_small("Wrong log input!");
						}
					}else {
                        for(Player player : server.games.get(0).players)player.client.writer.sendLetter(message);
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

    public void show_log(String[] message) {
		if(message.length < 2)return;
		String log ="LOG --> " + message[1] + " --> ";
		if(server.games.size() != 0)
		switch(message[1]) {
			case "date":
				log += server.games.get(0).time.getDate();
			break;
			case "clouds":
				if(message.length >= 3 && message[2].equals("loop")) 
					server.games.get(0).maps.maps[0].sky.printCloudsInLoop = true;
				else if(message.length >= 3 && message[2].equals("stop")) server.games.get(0).maps.maps[0].sky.printCloudsInLoop = false;
				else log += server.games.get(0).maps.maps[0].sky.printClouds();
			break;
			case "weather":
				if(message.length >= 3 && message[2].equals("loop")) server.games.get(0).maps.maps[0].sky.printWeatherInLoop = true;
				else if(message.length >= 3 && message[2].equals("stop")) server.games.get(0).maps.maps[0].sky.printWeatherInLoop = false;
				else log += server.games.get(0).maps.maps[0].sky.printWeathers();
			break;
			case "wind":
				log += server.games.get(0).maps.maps[0].sky.strengthOfWind;
			break;
			case "windDirection":
				if(message.length >= 3){
					int direction = Integer.parseInt(message[2]);
					if(direction < 0 || direction > 7) return;
					server.games.get(0).maps.maps[0].sky.directionOfWind = direction;
					log += "done";
				} 
				else log += server.games.get(0).maps.maps[0].sky.directionOfWind;
			break;
			case "health":
				log += server.games.get(0).players.get(0).actualCondition.getHealth();
			break;
			case "move":
				if(message.length == 4){
					server.games.get(0).players.get(0).setBehaviour(new Move(server.games.get(0), server.games.get(0).players.get(0),
					server.games.get(0).maps.maps[0].places[Integer.parseInt(message[2])][Integer.parseInt(message[3])]));
				}
			break;
			case "loop":
				if(message.length >= 3 && message[2].equals("loop")) server.games.get(0).loop.logLoopThread = true;
				else if(message.length >= 3 && message[2].equals("stop")) server.games.get(0).loop.logLoopThread = false;
			break;
			case "map":
				if(message.length >= 3)log = server.games.get(0).maps.maps[Integer.valueOf(message[2])].logMap();
				else log = server.games.get(0).maps.maps[0].logMap();
			default:
				return;
		}
		ConsolePrint.success(log);
	}
    
}