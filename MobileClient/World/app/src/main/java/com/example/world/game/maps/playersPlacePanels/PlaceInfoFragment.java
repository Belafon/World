package com.example.world.game.maps.playersPlacePanels;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.world.R;

import java.util.List;

public class PlaceInfoFragment extends Fragment {
    private Fragment previousFragment;
    private int fragmentContainerId;
    private String name;
    private String look;
    private List<PlayersPlaceEffect> placeEffects;

    public PlaceInfoFragment(
            Fragment previousFragment,
            int fragmentContainerId,
            String name,
            String look,
            List<PlayersPlaceEffect> placeEffects) {

        this.previousFragment = previousFragment;
        this.fragmentContainerId = fragmentContainerId;
        this.name = name;
        this.look = look;
        this.placeEffects = placeEffects;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_place_effect_info, container, false);

        TextView nameLabel = rootView.findViewById(R.id.nameLabel);
        nameLabel.setText(name.toString());
        nameLabel.setTypeface(nameLabel.getTypeface(), Typeface.BOLD);

        TextView lookLabel = rootView.findViewById(R.id.lookLabel);
        lookLabel.setText(look);

        Button backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        // get list of buttons of each place effects
        // for each place effect add button to the list
        LinearLayout placeEffectsList = rootView.findViewById(R.id.placeEffectsList);
        for (PlayersPlaceEffect effect : placeEffects) {
            Button placeEffectButton = new Button(this.getContext());
            placeEffectButton.setText(effect.name.name());
            placeEffectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayPlaceEffectInfo(effect);
                }
            });
            placeEffectsList.addView(placeEffectButton);
        }

        return rootView;
    }

    private void goBack() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, previousFragment);
        fragmentTransaction.commit();
    }

    private void displayPlaceEffectInfo(PlayersPlaceEffect effect) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, effect.getInfoFragment(this, fragmentContainerId));
        fragmentTransaction.commit();
    }

}