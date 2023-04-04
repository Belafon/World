package com.belafon.Server.Messages;

import com.belafon.Game.Creatures.Condition.Knowledge.Knowledge;

public interface ConditionCreatureMessages {
    public default void setHealth(int health) {
    }

    public default void setHunger(int hunger) {
    }

    public default void setBleeding(int bleeding) {
    }

    public default void setHeat(int heat) {
    }

    public default void setFatigueMax(int fatigueMax) {
    }

    public default void addKnowledge(Knowledge knowledge) {
    }

    public default void setCurrentEnergyOutput(int currentEnergyOutput) {
    }

    public default void setStrength(int strength) {
    }

    public default void setAgility(int agility) {
    }

    public default void setSpeedOfWalk(int speedOfWalk) {
    }

    public default void setHearing(int hearing) {
    }

    public default void setObservation(int observation) {
    }

    public default void setVision(int vision) {
    }

    public default void setCurrentSpeed(int currentSpeed) {
    }

    public default void setLoudness(int loudness) {
    }

    public default void setFatigue(int fatigue) {
    }

    public default void setAttention(int attention) {
    }

    public default void setSpeedOfRun(int speedOfRun) {
    }

}