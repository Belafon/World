package com.example.world.game.visibles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.world.R;

import java.util.Hashtable;

public abstract class VisiblesListFragment<T extends VisiblesInfoFragment> extends Fragment {
    private LinearLayout visiblesList;
    private Hashtable<Visible, T> visibleFragments = new Hashtable<>();
    private T selectedVisibleFragment;

    protected int fragmentContainerId;

    public VisiblesListFragment(int fragmentContainerId) {
        this.fragmentContainerId = fragmentContainerId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_visibles_list, container, false);

        visiblesList = rootView.findViewById(R.id.visibles_list);

        ScrollView scrollView = rootView.findViewById(R.id.scroll_view);

        return rootView;
    }

    protected void addVisiblesTitle(T visibleFragment, Runnable selectVisibleFragment) {
        visibleFragment.getTitleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVisibleFragment.run();
            }
        });

        visiblesList.addView(visibleFragment.getTitleView());
        visibleFragments.put(visibleFragment.getVisible(), visibleFragment);
    }

    protected void removeVisiblesTitle(Visible visible) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                visiblesList.removeView(visibleFragments.get(visible).getTitleView());
                visibleFragments.remove(visible);
            }
        });
    }

    protected void showVisiblesInfoFragment(T visibleInfoFragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, visibleInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
