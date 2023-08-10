package com.example.world.game.bodyStats;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;

import com.example.world.R;

public class CreatureStatisticsPanel extends Fragment {
    public CreatureStatisticsPanel(BodyStats stats) {
        this.stats = stats;
    }

    private BodyStats stats;
    private ListView actualList;
    private ListView abilityList;
    private ArrayAdapter<BodyStat> actualAdapter;
    private ArrayAdapter<BodyStat> abilityAdapter;
    private final static int ITEM_HEIGHT_IN_DP = 48;

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
        actualAdapter = new ArrayAdapter<>(requireContext(), R.layout.text_label,
                actualStats);
        actualList.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, actualStats.length * dpToPixels(ITEM_HEIGHT_IN_DP)));
        actualList.setAdapter(actualAdapter);
        //actualList.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button, null));

        // Set up the adapter for ability list
        BodyStat[] abilityStats = {
                stats.healthAbility, stats.strengthAbility, stats.agilityAbility, stats.speedOfWalkAbility,
                stats.speedOfRunAbility, stats.currentSpeedAbility, stats.hearingAbility, stats.observationAbility,
                stats.visionAbility, stats.loudnessAbility, stats.attentionAbility, stats.energyOutputAbility
        };
        abilityAdapter = new ArrayAdapter<>(requireContext(),
                R.layout.text_label, abilityStats);
        abilityList.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, abilityStats.length * dpToPixels(ITEM_HEIGHT_IN_DP)));

        abilityList.setAdapter(abilityAdapter);
        //abilityList.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button, null));


        
        stats.setAdapters(this);
        return view;
    }

    private int dpToPixels(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public void abilityStatsUpdate() {
        abilityAdapter.notify();
    }

    public void actualStatsUpdate() {
        actualAdapter.notify();
    }
    
    public ArrayAdapter<BodyStat> getActualAdapter() {
        return actualAdapter;
    }

    public ArrayAdapter<BodyStat> getAbilityAdapter() {
        return abilityAdapter;
    }
}
