package com.belafon.Server.Messages;

public interface ServerMessages {
    public default void setNumberOfPlayersInQueue(int number) {
    }
    public default void startGame() {
    }
}
