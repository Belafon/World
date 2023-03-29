package com.example.client.chatListener;

import com.example.Panels;
import com.example.Stats;

public class ChatListener {
    private Stats stats;

    public ChatListener(Stats stats) {
        this.stats = stats;
    }

    public void listen(String message, Panels panels) {
        String[] args = message.split(" ");
        panels.listenerPanel.addMessage(message);

        listenBase(args);
    }

    private void listenBase(String[] args) {
        switch (args[0]) {
            case "server" -> listenServer(args);
            case "item" -> listenItem(args);
            case "player" -> listenPlayer(args);
            case "map" -> listenMap(args);
            case "behaviour" -> listenBehaviour(args);
            case "surrounding" -> listenSurrounding(args);
        }
    }

    private void listenSurrounding(String[] args) {

    }

    private void listenBehaviour(String[] args) {
    }

    private void listenMap(String[] args) {
    }

    private void listenPlayer(String[] args) {
        switch (args[1]) {
            case "actualCondition" -> listenActualCondition(args);
            case "abilityCondition" -> listenabilityCondition(args);
            case "knowledge" -> listenKnowledge(args);
        }
    }

    private void listenabilityCondition(String[] args) {
        switch (args[2]) {
            case "strength" -> stats.body.setStrengthAbility(args[3]);
            case "agility" -> stats.body.setAgilityAbility(args[3]);
            case "speed_of_run" -> stats.body.setSpeedOfRunAbility(args[3]);
            case "speed_of_walk" -> stats.body.setSpeedOfWalkAbility(args[3]);
            case "hearing" -> stats.body.setHearingAbility(args[3]);
            case "observation" -> stats.body.setObservationAbility(args[3]);
            case "vision" -> stats.body.setVisionAbility(args[3]);
        }
        ;
    }

    private void listenActualCondition(String[] args) {
        switch (args[2]) {
            case "health" -> stats.body.setHealth(args[3]);
            case "hunger" -> stats.body.setHunger(args[3]);
            case "bleeding" -> stats.body.setBleeding(args[3]);
            case "heat" -> stats.body.setHeat(args[3]);
            case "fatigue_max" -> stats.body.setFatigueMax(args[3]);
            case "current_energy_output" -> stats.body.setCurrentEnergyOutput(args[3]);
        }
        ;
    }

    private void listenKnowledge(String[] args) {
    }

    private void listenItem(String[] args) {
    }

    private void listenServer(String[] args) {
    }
}
