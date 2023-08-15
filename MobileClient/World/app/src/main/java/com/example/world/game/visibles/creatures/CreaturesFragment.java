package com.example.world.game.visibles.creatures;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.world.AbstractActivity;
import com.example.world.R;
import com.example.world.game.Stats;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.example.world.game.visibles.Visibles;
import com.example.world.game.visibles.VisiblesListFragment;
import com.example.world.game.visibles.items.ItemInfoFragment;
import com.example.world.game.visibles.resources.Resource;
import com.example.world.game.visibles.resources.ResourceInfoFragment;


/**
 * list of visible creatures
 */
 public class CreaturesFragment extends VisiblesListFragment<CreaturesInfoFragment> {
    public CreaturesFragment(int fragmentContainerId, Visibles visibles, Fragment returnFragment) {
        super(fragmentContainerId, visibles, returnFragment);
    }

    @Override
    protected void initialize(Visibles visibles) {
        synchronized (visibles.creatures){
            for (Creature creature : visibles.creatures.values()) {
                addVisiblesTitle(creature);
            }
        }
    }

    public void addVisiblesTitle(Creature creature) {
        AbstractActivity.getActualActivity().runOnUiThread(()-> {
            CreaturesInfoFragment visiblesFragment = new CreaturesInfoFragment(returnFragment, fragmentContainerId, creature);
            addVisiblesTitle(visiblesFragment, () -> this.selectVisible(visiblesFragment));
        });
    }

    /**
     * Changes the concrete panel with detailed info about
     * one creature,
     * 
     * @param visiblesFragment
     */
    public void selectVisible(CreaturesInfoFragment visiblesFragment) {
        showVisiblesInfoFragment(visiblesFragment);
    }

    @Override
    protected void updateRemovedVisible(CreaturesInfoFragment infoFragment, BehavioursPossibleIngredient ingredient) {
        if(infoFragment.getVisible().equals(ingredient))
            infoFragment.goBack();

        infoFragment.updateIngredientRemoved(ingredient, infoFragment.getBehavioursList());
    }
}
