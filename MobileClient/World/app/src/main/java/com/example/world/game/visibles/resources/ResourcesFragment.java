package com.example.world.game.visibles.resources;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.world.R;
import com.example.world.game.visibles.Resource;
import com.example.world.game.visibles.VisiblesListFragment;
import com.example.world.game.visibles.items.ItemInfoFragment;

public class ResourcesFragment extends VisiblesListFragment<ResourceInfoFragment> {
    public ResourcesFragment(int fragmentContainerId) {
        super(fragmentContainerId);
    }


    public void addVisiblesTitle(Resource resource) {
        ResourceInfoFragment resourcesInfoFragment = new ResourceInfoFragment(this, fragmentContainerId, resource);
        addVisiblesTitle(resourcesInfoFragment, () -> this.selectVisible(resourcesInfoFragment));
    }

    /**
     * Changes the concrete panel with detailed info about one resource.
     *
     * @param resourcesInfoFragment The ResourcesInfoFragment to be selected.
     */
    public void selectVisible(ResourceInfoFragment resourcesInfoFragment) {
        showVisiblesInfoFragment(resourcesInfoFragment);
    }
}
