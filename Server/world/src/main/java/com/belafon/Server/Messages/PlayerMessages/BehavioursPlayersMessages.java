package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Game.Creatures.Behaviour.BehaviourType;
import com.belafon.Server.Messages.BehavioursMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;

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
