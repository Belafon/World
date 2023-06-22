package com.example.world.game.client;

import java.util.List;

import com.example.world.client.Client;
import com.example.world.game.behaviours.Behaviour;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public class BehavioursMessage {

    public void executeBehaviour(List<BehavioursPossibleIngredient> selectedIngredients, Behaviour behaviour) {
        Client.sendMessage("game behaviour executeBehaviour " + behaviour.messagesName + " " + writeIngredients(selectedIngredients));
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
