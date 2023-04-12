package com.belafon.Game.Creatures.Races.Animals.AnimalRaces;

import com.belafon.Game.Creatures.Races.Animals.TestingMessages;
import com.belafon.Server.SendMessage.MessageSender;

public class AnimalRace {
    public final String name;
    public final String description;
    public final MessageSender sendMessage;
    
    public AnimalRace(String name, String description, MessageSender sendMessage) {
        this.name = name;
        this.description = description;
        this.sendMessage = sendMessage;
    }

    public static final AnimalRace deer = new AnimalRace(
        "Deer", "Deers description", 
        TestingMessages.createMessageSender());
}
