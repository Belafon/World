package com.belafon.server.messages.playerMessages;

import com.belafon.server.messages.CreatureVisibleMessages;
import com.belafon.server.sendMessage.PlayersMessageSender;
import com.belafon.world.visibles.creatures.Creature;

public class CreatureVisiblePlayerMessages implements CreatureVisibleMessages {
    public final PlayersMessageSender sendMessage;

    public CreatureVisiblePlayerMessages(PlayersMessageSender sendMessage) {
        this.sendMessage = sendMessage;
    }
    
    @Override
    public void setCurrentEnergyOutput(int currentEnergyOutput, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " currentEnergyOutput " + currentEnergyOutput);
    }
    
    @Override
    public void setStrength(int strength, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " strength " + strength);
    }
    
    @Override
    public void setAgility(int agility, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " agility " + agility);
    }
    
    @Override
    public void setSpeedOfWalk(int speedOfWalk, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " speedOfWalk " + speedOfWalk);
    }
    
    @Override
    public void setSpeedOfRun(int speedOfRun, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " speedOfRun " + speedOfRun);
    }
    
    @Override
    public void setHearing(int hearing, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " hearing " + hearing);
    }
    
    @Override
    public void setObservation(int observation, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " observation " + observation);
    }
    
    @Override
    public void setVision(int vision, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " vision " + vision);
    }
    
    @Override
    public void setCurrentSpeed(int currentSpeed, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " currentSpeed " + currentSpeed);
    }
    
    @Override
    public void setLoudness(int loudness, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " loudness " + loudness);
    }
    
    @Override
    public void setHealth(int health, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " health " + health);
    }
    
    @Override
    public void setFatigue(int fatigue, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " fatigue " + fatigue);
    }
    
    @Override
    public void setAttention(int attention, Creature creature) {
        sendMessage.sendLetter("creatureVisible abilityCondition " + creature.id + " attention " + attention);
    }
    
    @Override
    public void behaviourChanged(Creature creature) {
        if (creature.currentBehaviour == null)
            sendMessage.sendLetter("creatureVisible currentBehaviour " + creature.id + " behaviour idle");
        else
            sendMessage.sendLetter("creatureVisible currentBehaviour " + creature.id + " behaviour " + creature.currentBehaviour.getType().behaviourClass.getSimpleName());
    }
}
