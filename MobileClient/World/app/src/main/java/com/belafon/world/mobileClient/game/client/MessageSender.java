package com.belafon.world.mobileClient.game.client;

public class MessageSender {

    public BehavioursMessage behaviours;
    public ServerMessages serverMessages;
    public MessageSender() {
        behaviours = new BehavioursMessage();
        serverMessages = new ServerMessages();
    }

}
