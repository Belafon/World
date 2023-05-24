package com.world.pcclient.client.chatListener;

import com.world.pcclient.Panels;
import com.world.pcclient.Stats;

public class ChatListener {
    private Stats stats;
    private Panels panels;

    public ChatListener(Stats stats, Panels panels) {
        this.stats = stats;
        this.panels = panels;
    }

    /**
     * Decompose the string message from the server.
     * 
     * @param message is get from the server.
     */
    public void listen(String message) {
        String[] args = message.split(" ");
        panels.listenerPanel.addMessage(message);

        try {
            listenBase(args);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
        switch (args[1]) {
            case "add_item_in_sight" -> stats.visibles.addItem(args, panels, stats.behaviours);
            case "add_creature_in_sight" -> stats.visibles.addCreature(args, panels, stats.behaviours);
            case "add_resource_in_sight" -> stats.visibles.addResource(args, panels, stats.behaviours);
            case "remove_item_in_sight" -> stats.visibles.removeItem(args, panels);
            case "remove_creature_in_sight" -> stats.visibles.removeCreature(args, panels);
            case "remove_resource_in_sight" -> stats.visibles.removeResource(args, panels);
        }
    }

    private void listenBehaviour(String[] args) {
    }

    private void listenMap(String[] args) {
        switch (args[1]) {
            case "look_arround" -> stats.maps.lookAroundSurroundingPlaces(args);
            case "new_map" -> stats.maps.addMap(args);
            case "weather" -> stats.maps.weather.setWeather(args);
            case "clouds" -> stats.maps.weather.setClouds(args);
            case "partOfDay" -> stats.maps.weather.setPartOfDay(args);
        }
    }

    private void listenPlayer(String[] args) {
        switch (args[1]) {
            case "actualCondition" -> listenActualCondition(args);
            case "abilityCondition" -> listenabilityCondition(args);
            case "knowledge" -> listenKnowledge(args);
            case "feasibleBehaviour" -> feasibleBehaviourUpdate(args);
        }
    }

    private void feasibleBehaviourUpdate(String[] args) {
        switch (args[2]) {
            case "add" -> stats.behaviours.addNewFeasibleBehaviour(args[3]);
            case "remove" -> stats.behaviours.removeFeasibleBehaviour(args[3]);
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
        switch (args[1]) {
            case "addItem" -> stats.inventory.addItem(
                    Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                    Integer.parseInt(args[6]), Integer.parseInt(args[7]),
                    args, stats.behaviours);
            case "removeItem" -> stats.inventory.removeItem(Integer.parseInt(args[2]));
        }
    }

    private void listenServer(String[] args) {
    }
}
