package com.example.world.game.client;

import com.example.world.client.Client;

public class ServerMessages {
    public void findMatch() {
        Client.sendMessage("server findTheMatch");
    }

    public void stopFindingMatch() {
        Client.sendMessage("server stopFindingTheMatch");
    }

    public void readyToStartTheGame() {
        Client.sendMessage("server ready");
    }
    public void disconnect() {
        Client.sendMessage("server disconnect");
    }
    
    public void setName(String name) {
        Client.sendMessage("server name " + name);
    }
}
