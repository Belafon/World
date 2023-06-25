package com.example.world.game.visibles.creatures;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.world.R;
import com.example.world.game.visibles.VisiblesInfoFragment;

public class CreaturesInfoFragment extends VisiblesInfoFragment<Creature> {

    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView idTextView;

    private LinearLayout titleView = super.createTitleView();

    public CreaturesInfoFragment(Fragment previousFragment, int fragmentContainerId, Creature creature) {
        super(previousFragment, fragmentContainerId, creature);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_creatures_info, container, false);

        nameTextView = rootView.findViewById(R.id.nameTextView);
        descriptionTextView = rootView.findViewById(R.id.descriptionTextView);
        idTextView = rootView.findViewById(R.id.idTextView);

        Button backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        updateViews();

        return rootView;
    }

    private void updateViews() {
        Creature creature = getVisible();
        if (creature != null) {
            nameTextView.setText("Name: " + creature.getName());
            descriptionTextView.setText("Description: " + creature.getLook());
            idTextView.setText("Id: " + creature.getId());
        }
    }


    @Override
    public View getTitleView() {
        return titleView;
    }
}
