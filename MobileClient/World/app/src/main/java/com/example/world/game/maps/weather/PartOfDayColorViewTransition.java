package com.example.world.game.maps.weather;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This handles many transitions committed by
 * more new parts of day.
 */
public class PartOfDayColorViewTransition extends ColorViewTransition {
    private static final String TAG = "PartOfDayColorViewTrans";
    private Queue<PartOfDay> colors = new LinkedList<>();
    private Color lastColorLevel;

    private DifferenceColorViewTransition currentTransition;
    private PartOfDay currentPartOfDay;

    public PartOfDayColorViewTransition(Color lastColorLevel) {
        this.lastColorLevel = lastColorLevel;
        this.currentColor = new Color(lastColorLevel.r, lastColorLevel.g, lastColorLevel.b, lastColorLevel.a);
    }

    public synchronized void addPartOfDay(PartOfDay partOfDay) {
        colors.add(partOfDay);

        if (currentTransition != null && !currentTransition.isTransitionDone())
            currentTransition.setHigherSpeedOfTransition(currentTransition.currentIdleCount / ((colors.size() + 1) * (colors.size() + 1)));
    }

    private int getSpeed(){
        if(currentTransition != null && !currentTransition.isTransitionDone())
            return currentTransition.currentIdleCount / ((colors.size() + 1) * (colors.size() + 1));
        return currentPartOfDay.durationInNuberOfChecks / ((colors.size() + 1) * (colors.size() + 1));
    }

    @Override
    public Color getColorUpdate() {
        if(currentTransition == null)
            return new Color(0, 0, 0, 0);
        return currentTransition.getColorUpdate();
    }

    private Color currentColor;
    @Override
    public synchronized void updateCurrentIdleCount() {
        if(currentTransition != null){
            Color updatedColor = currentTransition.getColorUpdate();
            currentColor = new Color(
                    currentColor.r + updatedColor.r,
                    currentColor.g + updatedColor.g,
                    currentColor.b + updatedColor.b,
                    currentColor.a + updatedColor.a
            );
        }

        if (currentPartOfDay == null
                || currentTransition.isTransitionDone()) {
            // move to the next part of day

            if (colors.isEmpty()){
                if(currentTransition != null){
                    lastColorLevel = currentPartOfDay.colorLevel;
                    currentTransition = new DifferenceColorViewTransition(new Color(0, 0,0 , 0), 1);
                }
                return;
            }

            if (currentPartOfDay != null)
                lastColorLevel = currentPartOfDay.colorLevel;

            currentPartOfDay = colors.poll();
            Log.d(TAG, "run: NEW PART OF DAY = " + currentPartOfDay.name.toString()
                    + " " + currentPartOfDay.colorLevel.r
                    + " " + currentPartOfDay.colorLevel.g
                    + " " + currentPartOfDay.colorLevel.b
                    + " " + currentPartOfDay.colorLevel.a);

            currentTransition = new DifferenceColorViewTransition(
                    getColorDifference(currentPartOfDay.colorLevel, currentColor),
                    getSpeed());
        }

        currentTransition.updateCurrentIdleCount();
    }

    private Color getColorDifference(Color first, Color second) {
        return new Color(
                first.r - second.r,
                first.g - second.g,
                first.b - second.b,
                first.a - second.a);
    }

    @Override
    public boolean isTransitionDone() {
        return false;
    }

    public void setFastestSpeedOfTransition() {
        currentTransition.setHigherSpeedOfTransition(1);
    }
}