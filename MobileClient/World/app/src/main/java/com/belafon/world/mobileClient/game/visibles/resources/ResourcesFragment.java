package com.example.world.game.visibles.resources;

import android.content.res.Resources;

import androidx.fragment.app.Fragment;

import com.example.world.AbstractActivity;
import com.example.world.game.Stats;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.example.world.game.visibles.Visibles;
import com.example.world.game.visibles.VisiblesListFragment;
import com.example.world.game.visibles.items.Item;

public class ResourcesFragment extends VisiblesListFragment<ResourceInfoFragment> {
    public ResourcesFragment(int fragmentContainerId, Visibles visibles, Fragment returnFragment) {
        super(fragmentContainerId, visibles, returnFragment);
        for(Resource resource : visibles.resources.values())
            addVisiblesTitle(resource);
    }

    @Override
    protected void initialize(Visibles visibles) {
        synchronized (visibles.resources){
            for (Resource resource : visibles.resources.values()) {
                addVisiblesTitle(resource);
            }
        }
    }

    public void addVisiblesTitle(Resource resource) {
        AbstractActivity.getActualActivity().runOnUiThread(() -> {
            ResourceInfoFragment resourcesInfoFragment = new ResourceInfoFragment(returnFragment, fragmentContainerId, resource);
            addVisiblesTitle(resourcesInfoFragment, () -> this.selectVisible(resourcesInfoFragment));
        });
    }

    /**
     * Changes the concrete fragment with detailed info about one resource.
     *
     * @param resourcesInfoFragment The ResourcesInfoFragment to be selected.
     */
    public void selectVisible(ResourceInfoFragment resourcesInfoFragment) {
        showVisiblesInfoFragment(resourcesInfoFragment);
    }

    @Override
    protected void updateRemovedVisible(ResourceInfoFragment infoFragment, BehavioursPossibleIngredient ingredient) {
        if(infoFragment.getVisible().equals(ingredient))
            infoFragment.goBack();

        infoFragment.updateIngredientRemoved(ingredient, infoFragment.behavioursFragment);
    }

}
