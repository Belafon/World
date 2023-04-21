package com.belafon.Server.MatchMakingSystems;

import com.belafon.Server.Client;

public abstract class MatchMakingSystem {
    public volatile int numberOfPlayers; // in queue

    public abstract void addClient(Client client);

    public abstract void removeClient(Client client);

    protected synchronized void setNumberOfPlayers(int numberOfPalyers) {
        this.numberOfPlayers = numberOfPalyers;
    }

    public enum Condition {
        idle,
        waitingInQueue,
        playing
    }
}
