package com.world.pcclient.client;

public class MessageSender {

    public BehavioursMessage behaviours;
    public MessageSender(Client client) {
        behaviours = new BehavioursMessage(client);
    }

}
