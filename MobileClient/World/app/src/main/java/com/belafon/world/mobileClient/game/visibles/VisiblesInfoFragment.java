package com.example.world.game.visibles;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

public abstract class VisiblesInfoFragment<T extends Visible> extends Fragment {
    private Fragment previousFragment;
    private int fragmentContainerId;
    protected T visible;

    public VisiblesInfoFragment(
            Fragment previousFragment,
            int fragmentContainerId,
            T visible) {
        this.visible = visible;
        this.previousFragment = previousFragment;
        this.fragmentContainerId = fragmentContainerId;
    }

    public void goBack() {
        if(isAdded()){
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(fragmentContainerId, previousFragment);
            fragmentTransaction.commit();
        }
    }

    public abstract String getTitleText();

    public T getVisible() {
        return visible;
    }

    /**
     * Title view in the context of the VIsibleListFragment,
     * this fragmnet is the only one.
     * 
     * This information is held just for removeing the title view
     * from the list.
     */
    private TextView titleView;

    public View getTitleView() {
        return titleView;
    }

    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }

}