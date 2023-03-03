package Server.Messages.PlayerMessages;

import Game.Creatures.Behaviour.BehaviourType;
import Server.Messages.BehavioursMessages;
import Server.SendMessage.SendMessagePlayer;

public class BehavioursPlayersMessages extends BehavioursMessages{
    public final SendMessagePlayer sendMessage;

    public BehavioursPlayersMessages(SendMessagePlayer sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void newFeasibleBehaviour(BehaviourType behaviourType) {
		sendMessage.sendLetter("player feasibleCondition add " + behaviourType.behaviourClass.getSimpleName());
    }
    
}
