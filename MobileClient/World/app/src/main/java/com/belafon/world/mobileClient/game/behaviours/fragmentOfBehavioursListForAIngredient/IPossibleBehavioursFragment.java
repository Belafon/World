package com.belafon.world.mobileClient.game.behaviours.fragmentOfBehavioursListForAIngredient;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.belafon.world.mobileClient.AbstractActivity;
import com.belafon.world.mobileClient.game.behaviours.Behaviours;
import com.belafon.world.mobileClient.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.belafon.world.mobileClient.game.behaviours.fragmentOfBehavioursListForAIngredient.ListOfBehavioursForSetOfIngredients;

/**
 * Lables that the fragment is able to show list of behaviours.
 */
public interface IPossibleBehavioursFragment {
    public default ListOfBehavioursForSetOfIngredients setPossibleBehavioursFragment(int fragmentContainer, BehavioursPossibleIngredient ingredient){
        ListOfBehavioursForSetOfIngredients listOfBehavioursFragment = new ListOfBehavioursForSetOfIngredients(ingredient, fragmentContainer);

        FragmentManager fragmentManager = ((FragmentActivity) AbstractActivity.getActualActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(fragmentContainer, listOfBehavioursFragment)
                .addToBackStack(null)
                .commit();
        return listOfBehavioursFragment;
    }

    public default void updateIngredientRemoved(BehavioursPossibleIngredient ingredient, ListOfBehavioursForSetOfIngredients fragment){
        if(fragment != null)
            fragment.removeIngredient(ingredient);
    }
}
