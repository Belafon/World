package com.example.world.game.behaviours;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.world.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BehavioursFragment extends Fragment {
    public static final Set<BehavioursExecutorFragment> EXECUTORS = new HashSet<>();
    private LinearLayout behavioursList;
    private Map<Behaviour, View> itemLabels = new HashMap<>();

    private int fragmentContainerId;

    public BehavioursFragment(int fragmentContainerId) {
        this.fragmentContainerId = fragmentContainerId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_behaviours, container, false);
        behavioursList = rootView.findViewById(R.id.behaviours_list);
        return rootView;
    }

    public void addItem(Behaviour item) {
        View itemLabel = createItemLabel(item);
        behavioursList.addView(itemLabel);
        itemLabels.put(item, itemLabel);
    }

    public void removeItem(Behaviour item) {
        View itemLabel = itemLabels.get(item);
        if (itemLabel != null) {
            behavioursList.removeView(itemLabel);
            itemLabels.remove(item);
        }
    }

    private View createItemLabel(Behaviour item) {
        TextView itemLabel = new TextView(requireContext());
        itemLabel.setText(item.name);
        itemLabel.setTypeface(null, Typeface.BOLD);

        final BehavioursFragment thisFragment = this;
        // Set up the view as needed for the item label
        itemLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of BehavioursExecutorFragment
                BehavioursExecutorFragment executorFragment = new BehavioursExecutorFragment(fragmentContainerId,
                        thisFragment, item);
                EXECUTORS.add(executorFragment);

                // Replace the existing fragment in the fragment container
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(fragmentContainerId, executorFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return itemLabel;
    }

    public void reupdateBehaviour() {
        for (BehavioursExecutorFragment executor : EXECUTORS) {
            executor.setBehaviour(executor.getCurrentlySelectedBehaviour());
        }
    }

    public void update(Behaviours behaviours) {
        for (BehavioursExecutorFragment executor : EXECUTORS) {
            executor.update(behaviours);
            for (View label : itemLabels.values()) {
                label.setEnabled(true);
            }
        }
    }
}
