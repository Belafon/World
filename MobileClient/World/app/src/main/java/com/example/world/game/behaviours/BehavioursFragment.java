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

/**
 * Fragment that displays a list of behaviours.
 * The instance should be created each time, when 
 * the fragment should be displayed.
 */
public class BehavioursFragment extends Fragment {
    // currently displayed behaviours executors
    public static final Set<BehavioursExecutorFragment> EXECUTORS = new HashSet<>();
    private LinearLayout behavioursList;
    private final Map<Behaviour, View> itemLabels = new HashMap<>();

    private int fragmentContainerId;
    private Set<Behaviour> feasibles;

    public BehavioursFragment(int fragmentContainerId, Set<Behaviour> feasibles) {
        this.fragmentContainerId = fragmentContainerId;
        this.feasibles = feasibles;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_behaviours, container, false);
        
        behavioursList = rootView.findViewById(R.id.behaviours_list);

        // fill the list with behaviours
        for (Behaviour behaviour : feasibles) {
            addItem(behaviour);
        }
        return rootView;
    }

    /**
     * Adds a new item to the list of behaviours,
     * when the fragment is displayed.
     * @param item
     */
    public void addItem(Behaviour item) {
        if (!this.isAdded())
            return;

        View itemLabel = createItemLabel(item);
        behavioursList.addView(itemLabel);
        itemLabels.put(item, itemLabel);
    }

    public void removeItem(Behaviour item) {
        if (!this.isAdded())
            return;

        View itemLabel = itemLabels.get(item);
        if (itemLabel != null) {
            behavioursList.removeView(itemLabel);
            itemLabels.remove(item);
        }
    }

    private View createItemLabel(Behaviour item) {
        TextView itemLabel = new TextView(requireContext());
        itemLabel.setText(item.name);
        itemLabel.setTextSize(16);
        itemLabel.setTypeface(null, Typeface.BOLD);
        itemLabel.setBackgroundResource(R.drawable.button);
        itemLabel.setTextColor(getResources().getColor(R.color.white));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Set the top margin to 20dp
        int marginInDp = 10; // Desired margin in dp
        float scale = getResources().getDisplayMetrics().density;
        int marginInPixels = (int) (marginInDp * scale + 0.5f);
        layoutParams.setMargins(0, marginInPixels, 0, 0);

        itemLabel.setLayoutParams(layoutParams);


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

    public static void reupdateBehaviour(Behaviours behaviours) {
        if(EXECUTORS.size() == 0)
            return;

        EXECUTORS.iterator().next().getActivity().runOnUiThread(() -> {
            for (BehavioursExecutorFragment executor : EXECUTORS) {
                executor.setBehaviour(executor.getCurrentlySelectedBehaviour(), behaviours);
            }
        });
    }

    /**
     * Updates all behaviours executor fragments,
     * that are currently displayed.
     * 
     * The executor fragment does not have to be binded 
     * with this BehavioursFragment.
     * 
     * @param behaviours
     */    
    public static void update(Behaviours behaviours) {
        if(EXECUTORS.size() == 0)
            return;

        EXECUTORS.iterator().next().getActivity().runOnUiThread(() -> {
            for (BehavioursExecutorFragment executor : EXECUTORS) {
                executor.update(behaviours);
            }
        });
    }
}
