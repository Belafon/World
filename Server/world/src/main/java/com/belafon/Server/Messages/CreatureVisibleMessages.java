package com.belafon.Server.Messages;

import com.belafon.Game.Creatures.Creature;

public interface CreatureVisibleMessages{
    public default void setCurrentEnergyOutput(int currentEnergyOutput, Creature creature) {
    }

    public default void setStrength(int strength, Creature creature) {
    }

    public default void setAgility(int agility, Creature creature) {
    }

    public default void setSpeedOfWalk(int speedOfWalk, Creature creature) {
    }

    public default void setSpeedOfRun(int speedOfRun, Creature creature) {
    }

    public default void setHearing(int hearing, Creature creature) {
    }

    public default void setObservation(int observation, Creature creature) {
    }

    public default void setVision(int vision, Creature creature) {
    }

    public default void setCurrentSpeed(int currentSpeed, Creature creature) {
    }

    public default void setLoudness(int loudness, Creature creature) {
    }

    public default void setHealth(int health, Creature creature) {
    }

    public default void setFatigue(int fatigue, Creature creature) {
    }

    public default void setAttention(int attention, Creature creature) {
    }

    public default void behaviourChanged(Creature creature) {
    }
}
