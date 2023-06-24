package com.example.world.game.bodyStats;

import android.widget.ArrayAdapter;

public class BodyStat {
    private final String name;
    private int value;
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
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
    }

    public int getValue() {
        return value;
    }

    public void setListAdapter(ArrayAdapter<BodyStat> listAdapter) {
        this.listAdapter = listAdapter;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
