package com.example.world.game.maps.weather;

import com.example.world.game.maps.weather.ColorViewTransition.Color;

public class Cloud {
    public final Color finalColor;
    public final int frequencyOfIdleClouds;
    public final int durationCloudTransition;
    public final int durationOfCloud;
    public final boolean isCloudy;
    public final boolean doesSunGoThrough;
    public final String description;

    public Cloud(Color finalColor, int frequencyOfIdleClouds,
            int durationCloudTransition, int durationOfCloud, boolean isCloudy,
            boolean doesSunGoThrough, String description) {
        this.finalColor = finalColor;
        this.frequencyOfIdleClouds = frequencyOfIdleClouds;
        this.durationCloudTransition = durationCloudTransition;
        this.durationOfCloud = durationOfCloud;
        this.isCloudy = isCloudy;
        this.description = description;
        this.doesSunGoThrough = doesSunGoThrough;
    }
}
