package com.example.world.gameActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.world.R;

public class MainMenuFragment extends Fragment {
    private GameActivity gameActivity;

    public MainMenuFragment(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        ImageButton characterButton = view.findViewById(R.id.character);
        characterButton.setOnClickListener(v -> onCharacter());

        ImageButton visiblesButton = view.findViewById(R.id.visibles);
        visiblesButton.setOnClickListener(v -> onVisibles());

        /*
         * ImageButton inventoryButton = view.findViewById(R.id.inventory);
         * inventoryButton.setOnClickListener(v -> onInventory());
         */ 
        ImageButton behaviourButton = view.findViewById(R.id.behaviour);
        behaviourButton.setOnClickListener(v -> onBehaviour());
          
        ImageButton mapButton = view.findViewById(R.id.map);
        mapButton.setOnClickListener(v -> onMap());
         
        ImageButton viewButton = view.findViewById(R.id.view);
        viewButton.setOnClickListener(v -> onView());

        return view;
    }

    private void onVisibles() {
        gameActivity.openFragment(gameActivity.game.panels.visibles);
    }

    public void onCharacter() {
        gameActivity.openFragment(gameActivity.game.panels.bodyStatistics);
    }

/*     public void onInventory() {
        gameActivity.openFragment(gameActivity.game.panels.inventory);
    }
 */
    public void onBehaviour() {
        gameActivity.openFragment(gameActivity.game.panels.behaviours);
    }

    public void onMap() {
        gameActivity.openFragment(gameActivity.game.panels.surroundingPlaces);
    }

    public void onView() {
        gameActivity.openFragment(gameActivity.game.panels.view);
    }
}
