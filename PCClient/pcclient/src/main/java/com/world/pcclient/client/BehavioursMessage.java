package com.world.pcclient.client;

import java.util.List;

import com.world.pcclient.behaviours.Behaviour;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public class BehavioursMessage {
    private Client client;

    public BehavioursMessage(Client client) {
        this.client = client;
    }

    public void executeBehaviour(List<BehavioursPossibleIngredient> selectedIngredients, Behaviour behaviour) {
        client.sendMessage("game behaviour executeBehaviour " + behaviour.messagesName + " " + writeIngredients(selectedIngredients));
    }

    private StringBuffer writeIngredients(List<BehavioursPossibleIngredient> selectedIngredients) {
        StringBuffer sb = new StringBuffer();
        for (BehavioursPossibleIngredient ingredient : selectedIngredients) {
            sb.append(ingredient.getVisibleType());
            sb.append("|");
            sb.append(ingredient.getId());
            sb.append(",");
        }
        return sb;
    }

}
