package com.example.world.game.maps.weather;

import com.example.world.game.maps.weather.ColorViewTransition.Color;
import com.example.world.likeliness.Dice;

import java.util.Set;

public class CloudsColorViewTransitions {
    private boolean isRaining = false;
    private boolean isCloudy = false;
    private boolean doesSunGoThrough = true;
    private int durationCloudTransition = 0;
    private int durationOfCloud = 0;
    private Color finalCloudsColor = new Color(0, 0, 0, 0);
    private Color finalWeatherColor = new Color(0, 0, 0, 0);

    private int frequencyOfIdleClouds = 0;
    private int frequencyOfIdleLightnings = 0;

    private Thread currentWorkThread;
    private Set<ColorViewTransition> colorViewTransitions;
    public CloudsColorViewTransitions(Set<ColorViewTransition> colorViewTransitions){
        this.colorViewTransitions = colorViewTransitions;
        new Thread(() -> {
            Thread.currentThread().setName("CloudsAndWeatherColorTransitions");
            while(true){
                boolean isNewTransition = handleTransitions();
                try {
                    if(isNewTransition){
                        currentWorkThread.join();
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private synchronized boolean handleTransitions() {
        boolean isNewTransition = false;
        if(isRaining){
            isNewTransition = decideIfStartNewChange(frequencyOfIdleLightnings);
            if(isNewTransition)
                runNewLightning();
        } else if(isCloudy && doesSunGoThrough){
            isNewTransition = decideIfStartNewChange(frequencyOfIdleClouds);
            if(isNewTransition)
                runNewCloud(finalCloudsColor, durationCloudTransition, durationOfCloud);
        } 
        return isNewTransition;
    }

    private void runNewLightning() {
        Thread lightningThread = new Thread(() -> {
            Dice dice3 = new Dice(3);
            Dice dice6 = new Dice(6);
            int toss = dice3.toss();
            ColorViewTransition transition = switch (toss){
                case 1 -> new CloudsColorViewTransition(new Color(300, 300, 400, -80), 1, 2 + dice6.toss());
                case 2 -> new CloudsColorViewTransition(new Color(200, 200, 300, -100), 1, 2 + dice6.toss());
                case 3 -> new CloudsColorViewTransition(new Color(100, 100, 200, -120), 1, 2 + dice6.toss());
                default -> null;
            };
            synchronized (colorViewTransitions){
                colorViewTransitions.add(transition);
            }

            sleepUntilTransitionDone(transition);
            currentWorkThread = null;
        });
        lightningThread.start();
        currentWorkThread = lightningThread;
    }

    private void runNewCloud(Color color, int durationOfTransition, int durationOfCloud){
        Thread cloudThread = new Thread(() -> {
            ColorViewTransition transition = new CloudsColorViewTransition(color, durationOfTransition, durationOfCloud);
            synchronized (colorViewTransitions){
                colorViewTransitions.add(transition);
            }
            sleepUntilTransitionDone(transition);
            currentWorkThread = null;
        });
        cloudThread.start();
        currentWorkThread = cloudThread;
    }

    private static void sleepUntilTransitionDone(ColorViewTransition transition) {
        while(!transition.isTransitionDone()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @returns true if new change should happen,
     * 1:frequency that true will be returned
     * If frequency is 0, false is returned automatically
     * If frequency is 1, true is returned automatically
     *  With higher frequency the probability of true is less.
     * */
    private static boolean decideIfStartNewChange(int frequencyOfIdle) {
        if(frequencyOfIdle == 0)
            return false;
        // 1 -> very often -> each time, // 2 -> 1:1
        Dice dice = new Dice(frequencyOfIdle);
        int number = dice.toss();
        if(number == 1)
            return true;
        return false;
    }

    public synchronized void setClouds(Cloud cloud) {
        Color lastFinalCloudsColor = this.finalCloudsColor;
        boolean didSunGoThrough = this.doesSunGoThrough;
        boolean wasCloudy = this.isCloudy;
        int lastDurationCloudTransition = this.durationCloudTransition;

        this.isCloudy = cloud.isCloudy;
        this.doesSunGoThrough = cloud.doesSunGoThrough;
        waitUntilCloudOrLightningEnds();
        this.finalCloudsColor = cloud.finalColor;
        this.frequencyOfIdleLightnings = 0;
        this.frequencyOfIdleClouds = cloud.frequencyOfIdleClouds;
        this.durationCloudTransition = cloud.durationCloudTransition;
        this.durationOfCloud = cloud.durationOfCloud;

        if(!doesSunGoThrough){
            if(lastFinalCloudsColor == null || didSunGoThrough){
                synchronized (colorViewTransitions){
                    colorViewTransitions.add(new DifferenceColorViewTransition(
                        finalCloudsColor, durationCloudTransition));
                }
            } else {
                synchronized (colorViewTransitions){
                    colorViewTransitions.add(new DifferenceColorViewTransition(
                        new Color(finalCloudsColor.r - lastFinalCloudsColor.r,
                                finalCloudsColor.g - lastFinalCloudsColor.g,
                                finalCloudsColor.b - lastFinalCloudsColor.b,
                                finalCloudsColor.a - lastFinalCloudsColor.a), 
                        durationCloudTransition));
                }
            }
        } else if(wasCloudy && !didSunGoThrough) {
            // return to 0 change
            synchronized (colorViewTransitions){
                colorViewTransitions.add(new DifferenceColorViewTransition(
                        new Color(-lastFinalCloudsColor.r,
                                -lastFinalCloudsColor.g,
                                -lastFinalCloudsColor.b,
                                -lastFinalCloudsColor.a), 
                        lastDurationCloudTransition));
            }
        }
    
    }
    

    private void waitUntilCloudOrLightningEnds() {
        if(currentWorkThread != null){
            try {
                currentWorkThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void setWeather(Weather.WeatherType type){
        if(type == Weather.WeatherType.idle){
            isRaining = false;
            synchronized (colorViewTransitions){
                colorViewTransitions.add(new DifferenceColorViewTransition(
                        new Color(-finalWeatherColor.r,
                                -finalWeatherColor.g,
                                -finalWeatherColor.b,
                                -finalWeatherColor.a), 10));
            }
            this.finalWeatherColor = type.color;
            return;
        }

        isRaining = true;
        waitUntilCloudOrLightningEnds();
        synchronized (colorViewTransitions){
            colorViewTransitions.add(new DifferenceColorViewTransition(
                new Color(type.color.r - this.finalWeatherColor.r,
                        type.color.g - this.finalWeatherColor.g,
                        type.color.b - this.finalWeatherColor.b,
                        type.color.a - this.finalWeatherColor.a), 
                10));
        }
        this.frequencyOfIdleLightnings = type.frequencyOfLightnings;
        this.finalWeatherColor = type.color;
    }
}
