package com.example.world.game.visibles;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.LinearLayout;

public abstract class VisiblesInfoFragment<T extends Visible> extends Fragment {
    private Fragment previousFragment;
    private int fragmentContainerId;
    private T visible;

    public VisiblesInfoFragment(
            Fragment previousFragment,
            int fragmentContainerId,
            T visible) {
        this.visible = visible;
        this.previousFragment = previousFragment;
        this.fragmentContainerId = fragmentContainerId;
    }

    protected void goBack() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, previousFragment);
        fragmentTransaction.commit();
    }

    public abstract View getTitleView();
    public T getVisible() {
        return visible;
    }

    protected LinearLayout createTitleView() {
        LinearLayout titleView = new LinearLayout(getContext());
        titleView.setOrientation(LinearLayout.VERTICAL);
        titleView.setPadding(10, 10, 10, 10);
        titleView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        return titleView;
    }
}