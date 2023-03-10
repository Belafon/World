package Server.Messages.PlayerMessages;

import Game.Creatures.Behaviour.BehaviourType;
import Server.Messages.BehavioursMessages;
import Server.SendMessage.PlayersMessageSender;

public class BehavioursPlayersMessages extends BehavioursMessages{
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
