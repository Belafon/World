package com.example.world.game.visibles.creatures;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.world.R;
import com.example.world.game.visibles.VisiblesListFragment;
import com.example.world.game.visibles.resources.ResourceInfoFragment;


/**
 * list of visible creatures
 */
 public class CreaturesFragment extends VisiblesListFragment<CreaturesInfoFragment> {
    public CreaturesFragment(int fragmentContainerId) {
        super(fragmentContainerId);
    }


    public void addVisiblesTitle(Creature creature) {
        CreaturesInfoFragment visiblesFragment = new CreaturesInfoFragment(this, fragmentContainerId, creature);
        addVisiblesTitle(visiblesFragment, () -> this.selectVisible(visiblesFragment));
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

}
