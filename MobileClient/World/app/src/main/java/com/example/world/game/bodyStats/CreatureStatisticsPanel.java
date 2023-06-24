package com.example.world.game.bodyStats;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.world.R;

public class CreatureStatisticsPanel extends Fragment {
    public CreatureStatisticsPanel(BodyStats stats) {
        this.stats = stats;
    }
    private BodyStats stats;
    private ListView actualList;
    private ListView abilityList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creature_statistics_panel, container, false);

        // Find the actual and ability list views in the layout
        actualList = view.findViewById(R.id.actual_list);
        abilityList = view.findViewById(R.id.ability_list);

        // Set up the adapter for actual list
        BodyStat[] actualStats = { stats.hunger, stats.fatigueMax, stats.heat, stats.bleeding };
        ArrayAdapter<BodyStat> actualAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,
                actualStats);
        actualList.setAdapter(actualAdapter);
        actualList.setBackgroundColor(Color.rgb(255, 255, 200));

        // Set up the adapter for ability list
        BodyStat[] abilityStats = {
                stats.healthAbility, stats.strengthAbility, stats.agilityAbility, stats.speedOfWalkAbility,
                stats.speedOfRunAbility, stats.currentSpeedAbility, stats.hearingAbility, stats.observationAbility,
                stats.visionAbility, stats.loudnessAbility, stats.attentionAbility, stats.energyOutputAbility
        };
        ArrayAdapter<BodyStat> abilityAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, abilityStats);
        abilityList.setAdapter(abilityAdapter);
        abilityList.setBackgroundColor(Color.rgb(200, 255, 255));

        return view;
    }
}
