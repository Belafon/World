package com.example.bodyStats;

import javax.swing.JList;

public class BodyStats {
    public JList<BodyStat> abilityList;
    public JList<BodyStat> actualList;

    // Create the ability list
    BodyStat healthAbility;
    BodyStat strengthAbility;
    BodyStat agilityAbility;
    BodyStat speedOfWalkAbility;
    BodyStat speedOfRunAbility;
    BodyStat currentSpeedAbility;
    BodyStat hearingAbility;
    BodyStat observationAbility;
    BodyStat visionAbility;
    BodyStat loudnessAbility;
    BodyStat attentionAbility;
    BodyStat energyOutputAbility;

    // Create the actual list
    BodyStat hunger;
    BodyStat fatigueMax;
    BodyStat heat;
    BodyStat bleeding;

    public BodyStats() {
        this.healthAbility = new BodyStat("Helath", 0);
        this.strengthAbility = new BodyStat("Strength", 0);
        this.agilityAbility = new BodyStat("Agility", 0);
        this.speedOfWalkAbility = new BodyStat("Walk Speed", 0);
        this.speedOfRunAbility = new BodyStat("Run Speed", 0);
        this.currentSpeedAbility = new BodyStat("Current Speed", 0);
        this.hearingAbility = new BodyStat("Hearing", 0);
        this.observationAbility = new BodyStat("Observation", 0);
        this.visionAbility = new BodyStat("Vision", 0);
        this.loudnessAbility = new BodyStat("Loudness", 0);
        this.attentionAbility = new BodyStat("Attention", 0);
        this.energyOutputAbility = new BodyStat("Energy Output", 0);
    
        // Create the actual list
        this.hunger = new BodyStat("Hunger", 0);
        this.fatigueMax = new BodyStat("Fatigue Max", 0);
        this.heat = new BodyStat("Heat", 0);
        this.bleeding = new BodyStat("Bleeding", 0);
    }
    public void setStatsLists() {
        this.healthAbility.setList(abilityList);
        this.strengthAbility.setList(abilityList);
        this.agilityAbility.setList(abilityList);
        this.speedOfWalkAbility.setList(abilityList);
        this.speedOfRunAbility .setList(abilityList);
        this.currentSpeedAbility.setList(abilityList);
        this.hearingAbility.setList(abilityList);
        this.observationAbility.setList(abilityList);
        this.visionAbility.setList(abilityList);
        this.loudnessAbility.setList(abilityList);
        this.attentionAbility.setList(abilityList);
        this.energyOutputAbility.setList(abilityList);
    
        // Create the actual list
        this.hunger.setList(actualList);
        this.fatigueMax.setList(actualList);
        this.heat.setList(actualList);
        this.bleeding.setList(actualList);
    }

    public void setHealth(String healthAbility) throws NumberFormatException {
        this.healthAbility.setValue(healthAbility);
    }

    public void setStrengthAbility(String strengthAbility) throws NumberFormatException {
        this.strengthAbility.setValue(strengthAbility);
    }

    public void setAgilityAbility(String agilityAbility) throws NumberFormatException {
        this.agilityAbility.setValue(agilityAbility);
    }

    public void setSpeedOfWalkAbility(String speedOfWalkAbility) throws NumberFormatException {
        this.speedOfWalkAbility.setValue(speedOfWalkAbility);
    }

    public void setSpeedOfRunAbility(String speedOfRunAbility) throws NumberFormatException {
        this.speedOfRunAbility.setValue(speedOfRunAbility);
    }

    public void setCurrentSpeedAbility(String currentSpeedAbility) throws NumberFormatException {
        this.currentSpeedAbility.setValue(currentSpeedAbility);
    }

    public void setHearingAbility(String hearingAbility) throws NumberFormatException {
        this.hearingAbility.setValue(hearingAbility);
    }

    public void setObservationAbility(String observationAbility) throws NumberFormatException {
        this.observationAbility.setValue(observationAbility);
    }

    public void setVisionAbility(String visionAbility) throws NumberFormatException {
        this.visionAbility.setValue(visionAbility);
    }

    public void setLoudnessAbility(String loudnessAbility) throws NumberFormatException {
        this.loudnessAbility.setValue(loudnessAbility);
    }

    public void setAttentionAbility(String attentionAbility) throws NumberFormatException {
        this.attentionAbility.setValue(attentionAbility);
    }

    public void setEnergyOutputAbility(String energyOutputAbility) throws NumberFormatException {
        this.energyOutputAbility.setValue(energyOutputAbility);
    }

    public void setHunger(String hungerAbility) throws NumberFormatException {
        this.hunger.setValue(hungerAbility);
    }

    public void setBleeding(String bleedingAbility) throws NumberFormatException {
        this.bleeding.setValue(bleedingAbility);
    }

    public void setHeat(String heatAbility) throws NumberFormatException {
        this.heat.setValue(heatAbility);
    }

    public void setFatigueMax(String fatigueMaxAbility) throws NumberFormatException {
        this.fatigueMax.setValue(fatigueMaxAbility);
    }


    public void setCurrentEnergyOutput(String currentEnergyOutputAbility) throws NumberFormatException {
        this.energyOutputAbility.setValue(currentEnergyOutputAbility);
    }
}
