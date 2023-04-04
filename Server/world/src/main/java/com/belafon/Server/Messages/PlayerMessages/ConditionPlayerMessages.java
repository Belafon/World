package com.belafon.Server.Messages.PlayerMessages;

import com.belafon.Game.Creatures.Condition.Knowledge.Knowledge;
import com.belafon.Server.Messages.ConditionCreatureMessages;
import com.belafon.Server.SendMessage.PlayersMessageSender;

public class ConditionPlayerMessages implements ConditionCreatureMessages {
    public final PlayersMessageSender sendMessage;

    public ConditionPlayerMessages(PlayersMessageSender sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public void setHealth(int health) {
        sendMessage.sendLetter("player actualCondition health " + health);
    }

    @Override
    public void setHunger(int hunger) {
        sendMessage.sendLetter("player actualCondition hunger " + hunger);
    }

    @Override
    public void setBleeding(int bleeding) {
        sendMessage.sendLetter("player actualCondition bleeding " + bleeding);
    }

    @Override
    public void setHeat(int heat) {
        sendMessage.sendLetter("player actualCondition heat " + heat);
    }

    @Override
    public void setFatigueMax(int fatigueMax) {
        sendMessage.sendLetter("player actualCondition fatigueMax " + fatigueMax);
    }

    @Override
    public void addKnowledge(Knowledge knowledge) {
        sendMessage.sendLetter("player knowledge add " + knowledge.degree + " " + knowledge.type.name);
    }

    @Override
    public void setCurrentEnergyOutput(int currentEnergyOutput) {
        sendMessage.sendLetter("player abilityCondition currentEnergyOutput " + currentEnergyOutput);
    }

    @Override
    public void setStrength(int strength) {
        sendMessage.sendLetter("player abilityCondition strength " + strength);
    }

    @Override
    public void setAgility(int agility) {
        sendMessage.sendLetter("player abilityCondition agility " + agility);
    }

    @Override
    public void setSpeedOfWalk(int speedOfWalk) {
        sendMessage.sendLetter("player abilityCondition speedOfWalk " + speedOfWalk);
    }

    @Override
    public void setHearing(int hearing) {
        sendMessage.sendLetter("player abilityCondition hearing " + hearing);
    }

    @Override
    public void setObservation(int observation) {
        sendMessage.sendLetter("player abilityCondition observation " + observation);
    }

    @Override
    public void setVision(int vision) {
        sendMessage.sendLetter("player abilityCondition vision " + vision);
    }

    @Override
    public void setCurrentSpeed(int currentSpeed) {
        sendMessage.sendLetter("player abilityCondition currentSpeed " + currentSpeed);
    }

    @Override
    public void setLoudness(int loudness) {
        sendMessage.sendLetter("player abilityCondition loudness " + loudness);
    }

    @Override
    public void setFatigue(int fatigue) {
        sendMessage.sendLetter("player abilityCondition fatigue " + fatigue);
    }

    @Override
    public void setAttention(int attention) {
        sendMessage.sendLetter("player abilityCondition attention " + attention);
    }

    @Override
    public void setSpeedOfRun(int speedOfRun) {
        sendMessage.sendLetter("player abilityCondition speedOfRun " + speedOfRun);
    }
}
