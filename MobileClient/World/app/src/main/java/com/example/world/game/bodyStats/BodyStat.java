package com.example.world.game.bodyStats;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

public class BodyStat {
    private final String name;
    private int value;
    private CreatureStatisticsPanel context;
    private ArrayAdapter<BodyStat> listAdapter;

    public BodyStat(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) throws NumberFormatException {
        this.value = Integer.parseInt(value);
        synchronized (this) {
            if (listAdapter != null) {
                Activity activity =  context.getActivity();
                if(activity != null){
                    context.getActivity().runOnUiThread(
                            () -> listAdapter.notifyDataSetChanged());
                }
            }
        }
    }

    public int getValue() {
        return value;
    }

    public void setListAdapter(ArrayAdapter<BodyStat> listAdapter, CreatureStatisticsPanel context) {
        synchronized (this) {
            this.listAdapter = listAdapter;
            this.context = context;
        }
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
