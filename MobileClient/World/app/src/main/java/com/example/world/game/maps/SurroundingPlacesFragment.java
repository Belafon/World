package com.example.world.game.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.world.R;
import com.example.world.game.maps.playersPlacePanels.PlaceInfoFragment;
import com.example.world.game.maps.playersPlacePanels.PlacePanel;

public class SurroundingPlacesFragment extends Fragment {
    private static final int SIZE_OF_GAP_IN_PIXELS = 15;
    private static final int SIZE_OF_BUTTON_IN_PIXELS = 100;

    private SurroundingMap map;
    private int fragmentContainerId;
    private Fragment previousFragment;

    public SurroundingPlacesFragment(SurroundingMap map, int fragmentContainerId, Fragment previousFragment) {
        this.map = map;
        this.fragmentContainerId = fragmentContainerId;
        this.previousFragment = previousFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_surrounding_places, container, false);

        GridLayout gridLayout = rootView.findViewById(R.id.gridLayout);

        for (int x = 0; x < map.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS; x++) {
            for (int y = 0; y < map.NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS; y++) {
                Button placeButton = new Button(getActivity());

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.height = SIZE_OF_BUTTON_IN_PIXELS;
                layoutParams.width = SIZE_OF_BUTTON_IN_PIXELS;
                layoutParams.setMargins(SIZE_OF_GAP_IN_PIXELS, SIZE_OF_GAP_IN_PIXELS, 0, 0); // Set margins for the gap
                placeButton.setLayoutParams(layoutParams);

                if (map.getPlacePanel(x, y) == null) {
                    placeButton.setBackgroundColor(Place.UNKNOWN.typePlace.backgroundColor);
                    placeButton.setOnClickListener(new PlaceButtonClickListener(x, y, Place.UNKNOWN, this));
                } else {
                    placeButton.setBackgroundColor(this.map.getPlacePanel(x, y).typePlace.backgroundColor);
                    placeButton.setOnClickListener(new PlaceButtonClickListener(x, y, map.getPlacePanel(x, y), this));
                }
                gridLayout.addView(placeButton);
            }
        }

        return rootView;
    }

    private class PlaceButtonClickListener implements View.OnClickListener {
        private int x;
        private int y;
        private Place place;
        private SurroundingPlacesFragment surroundingPlacesFragment;

        PlaceButtonClickListener(int x, int y, Place place, SurroundingPlacesFragment surroundingPlacesFragment) {
            this.x = x;
            this.y = y;
            this.place = place;
            this.surroundingPlacesFragment = surroundingPlacesFragment;
        }

        @Override
        public void onClick(View v) {
            PlaceInfoFragment placeInfoFragment = place.getInfoFragment(surroundingPlacesFragment, fragmentContainerId);
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(fragmentContainerId, placeInfoFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
