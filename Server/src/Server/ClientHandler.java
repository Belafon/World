package Server;

import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final Client client;
    public ClientHandler(Server server, Socket socket, Client client) {
        this.clientSocket = socket;
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {

    }    
}
