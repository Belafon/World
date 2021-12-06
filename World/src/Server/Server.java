package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;

import Console.ConsoleListener;
import Console.ConsolePrint;
/*import Game.Items.Items_Clothes.Leather_jacket;
import Game.Items.Items_Food.Apple;
import Game.Players.Player;
import Game.Time.Timer;
import Game.maps.Place;*/
import Game.Time.Clocks;
import Game.Game;
import Server.MatchMakingSystems.BasicMatchMakingSystem;
import Server.MatchMakingSystems.MatchMakingSystem;

public class Server {
	private ServerSocket serverSocket;
	private int port = 25555;
	public volatile boolean isServerRunning = true;

	public final ArrayList<Game> games = new ArrayList<Game>();
	public final int numberOfPlayersForStartTheGame = 1;
	public final MatchMakingSystem matchMaking = new BasicMatchMakingSystem(this, numberOfPlayersForStartTheGame);
	public final ConsoleListener consoleListener = new ConsoleListener(this);
	public final Hashtable<String, Client> allClients = new Hashtable<String, Client>();
	public final Clocks clocks = new Clocks();

	public Server() {
		printIpAdresses();
		
		createServerSocket();
		
		clocks.start();
		
		Thread thread = new Thread(consoleListener);
		thread.start();


		ConsolePrint.serverInfo("Server is waiting for clients...");
		while(isServerRunning) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				ConsolePrint.error_big("Server: Server stoped new client acception! ");
				e.printStackTrace();
			}
			final Socket clientSocket = socket;
			final Server server = this;
			new Thread(new Runnable() {
				@Override
				public void run() {
					String ip = clientSocket.getInetAddress().getHostAddress().toString();
					Client client = allClients.get(ip);

					if(client == null){
						ConsolePrint.serverInfo("Server: New device connected :: " + ip);
						client = createClient(clientSocket);
						new GetMessage(clientSocket, server, client);	 // recever of new messages
					} else {
						// client has connected again
						ConsolePrint.serverInfo("Server: Device connected again :: " + ip);
						client.bindNewSendMessage(clientSocket);
						if(client.disconnected) new GetMessage(clientSocket, server, client);
					}
				}
			}).start();
		}
			
	}

	public Client createClient(Socket clientSocket) {
		Client client = new Client(clientSocket);
		allClients.put(client.ipAddress, client);
		ConsolePrint.serverInfo("New client created");
		return client;
    }
	
	private void createServerSocket() {
		while(true) {
			try {
				serverSocket = new ServerSocket(port);
				break;
			} catch(Exception e) {
				port++;
			}
		}
		ConsolePrint.serverInfo("Server: New Server created on port " + port);
	}

	private void printIpAdresses() {
		try {
			ConsolePrint.serverInfo("Local ip = " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			ConsolePrint.error("Local ip was not gotten");
		}
		
		try {
			ConsolePrint.serverInfo("External ip = " + getExternalIp());
		} catch (Exception e) {
			ConsolePrint.error("External ip was not gotten");
		}
	}

	private String getExternalIp() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}


	 
	/*
	 * getter get_type_of_weather nothing
	 * getter get_type_of_weather rain
	 * getter get_type_of_weather heavy_rain
	 * getter get_type_of_weather storm
	 * getter get_type_of_weather thunderstorm
	 * 
	 * getter get_part_of_day sunset_1
	 * getter get_part_of_day sunset_2
	 * getter get_part_of_day after_midnight
	 * getter get_part_of_day sunrise_1
	 * getter get_part_of_day sunrise_2
	 * getter get_part_of_day morning
	 * 
	 * getter get_clouds 0
	 * 
	 * setter putOffGear 3
	 * 
	 * behaviour move 25 1.5f
	 * behaviour eat 2
	 * behaviour explore_surrounding take_notice 20 Items_Food.Apple
	 */
}
