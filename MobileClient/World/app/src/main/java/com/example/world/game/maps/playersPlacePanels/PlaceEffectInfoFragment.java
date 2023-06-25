package com.example.world.game.maps.playersPlacePanels;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.world.R;

public class PlaceEffectInfoFragment extends Fragment {

    private Fragment previousFragment;
    private int fragmentContainerId;
    private PlayersPlaceEffect.PlaceEffectName name;
    private String look;

    public PlaceEffectInfoFragment(
            Fragment previousFragment,
            int fragmentContainerId,
            PlayersPlaceEffect.PlaceEffectName name,
            String look) {

        this.previousFragment = previousFragment;
        this.fragmentContainerId = fragmentContainerId;
        this.name = name;
        this.look = look;
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

        return rootView;
    }

    private void goBack() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, previousFragment);
        fragmentTransaction.commit();
    }
}
