package com.example.world.game.client;

public class MessageSender {

    public BehavioursMessage behaviours;
    public MessageSender(Client client) {
        behaviours = new BehavioursMessage(client);
    }

}
