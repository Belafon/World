package com.example.world.game.visibles.items;

import android.os.Bundle;

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
import com.example.world.game.visibles.resources.Resource;

/**
 * list of visible items
 */
public class ItemFragment extends VisiblesListFragment<ItemInfoFragment> {
    public ItemFragment(int fragmentContainerId, Visibles visibles, Fragment returnFragment) {
        super(fragmentContainerId, visibles, returnFragment);
    }

    @Override
    protected void initialize(Visibles visibles) {
        synchronized (visibles.items){
            for (Item item : visibles.items.values()) {
                addVisiblesTitle(item);
            }
        }
    }

    public void addVisiblesTitle(Item item) {
        AbstractActivity.getActualActivity().runOnUiThread(() -> {
            ItemInfoFragment itemsInfoFragment = new ItemInfoFragment(returnFragment, fragmentContainerId, item);
            addVisiblesTitle(itemsInfoFragment, () -> this.selectVisible(itemsInfoFragment));
        });
    }

    /**
     * Changes the concrete panel with detailed info about one item.
     *
     * @param itemsInfoFragment The ItemsInfoFragment to be selected.
     */
    public void selectVisible(ItemInfoFragment itemsInfoFragment) {
        showVisiblesInfoFragment(itemsInfoFragment);
    }

    @Override
    protected void updateRemovedVisible(ItemInfoFragment infoFragment, BehavioursPossibleIngredient ingredient) {
        if(infoFragment.getVisible().equals(ingredient))
            infoFragment.goBack();

        infoFragment.updateIngredientRemoved(ingredient, infoFragment.getBehavioursList());
    }

}