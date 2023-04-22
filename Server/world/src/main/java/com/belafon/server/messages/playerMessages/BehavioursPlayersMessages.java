package com.belafon.server.messages.playerMessages;

import com.belafon.server.messages.BehavioursMessages;
import com.belafon.server.sendMessage.PlayersMessageSender;
import com.belafon.world.creatures.behaviour.BehaviourType;

public class BehavioursPlayersMessages implements BehavioursMessages{
    public final PlayersMessageSender sendMessage;

    public BehavioursPlayersMessages(PlayersMessageSender sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void newFeasibleBehaviour(BehaviourType behaviourType) {
		sendMessage.sendLetter("player feasibleCondition add " + behaviourType.behaviourClass.getSimpleName());
    }

    @Override
    public void removeFeasibleBehaviour(BehaviourType behaviourType) {
        sendMessage.sendLetter("player feasibleCondition remove " + behaviourType.behaviourClass.getSimpleName());
    }
    
}