package com.example.world.game.visibles.items;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.world.R;
import com.example.world.game.visibles.VisiblesListFragment;

/**
 * list of visible items
 */
public class ItemFragment extends VisiblesListFragment<ItemInfoFragment> {
    public ItemFragment(int fragmentContainerId) {
        super(fragmentContainerId);
    }


    public void addVisiblesTitle(Item item) {
        ItemInfoFragment itemsInfoFragment = new ItemInfoFragment(this, fragmentContainerId, item);
        addVisiblesTitle(itemsInfoFragment, () -> this.selectVisible(itemsInfoFragment));
    }

    /**
     * Changes the concrete panel with detailed info about one item.
     *
     * @param itemsInfoFragment The ItemsInfoFragment to be selected.
     */
    public void selectVisible(ItemInfoFragment itemsInfoFragment) {
        showVisiblesInfoFragment(itemsInfoFragment);
    }
}