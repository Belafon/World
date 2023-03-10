package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Console.ConsolePrint;
import Server.MatchMakingSystems.MatchMakingSystem;

public class MessageReceiver implements Runnable{
    public Client client;
    private Socket clientSocket;
	boolean isInterupted = false;

    public MessageReceiver(Socket clientSocket, Server server, Client client) {
        this.client = client;
        this.clientSocket = clientSocket;
        client.disconnected = false;

        // starts to receave the messages from the client
        server.executor.submit(this);
    }
    
    
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Scanner scanner = new Scanner(in);) {
            while (clientSocket.isConnected()) {
                String message = null;
                try {
                    // lets listen the message from client
                    message = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    // if new line was not found, this listener will be canceled
                    if (client != null) {
                        ConsolePrint.error_small("Client has disconnected");
                        client.disconnected = true;
                        if (client.queueCondition == MatchMakingSystem.Condition.waitingInQueue)
                            client.getServer().matchMaking.removeClient(client); // remove from queue
                    }
                    break;
                }
                if (message != null)
                    decomposeTheString(message, clientSocket, client.getServer());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
    
	
	public void decomposeTheString(String value, Socket clientSocket, Server server) {
		String[] message = value.split(" ");
		ConsolePrint.new_message(value, clientSocket.getInetAddress().getHostAddress().toString());
		switch(message[0]) {
		case "game":
		getGameMessage(message, clientSocket, server);
		break;
		case "server":
		getServerMessage(message, clientSocket, server);
		break;
		}
	}

	private void getServerMessage(String[] message, Socket clientSocket, Server server) {
		switch(message[1]) {
			case "findTheMatch":
			server.matchMaking.addClient(client);
			break;
			case "stopFindingTheMatch":
			server.matchMaking.removeClient(client);
			break;
			case "name":
			if(message.length > 2)
			client.name = message[2];
			break;
			case "disconnect":
			switch(client.queueCondition){
				case playing:
					client.disconnected = true;
				break;
				case waitingInQueue:
					server.matchMaking.removeClient(client);
				case idle:
					server.allClients.remove(client.ipAddress);
				try {
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			break;
		}
	}

	private void getGameMessage(String[] message, Socket clientSocket, Server server) {
		switch(message[1]) {

		}
	}

}
