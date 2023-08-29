package com.belafon.world.mobileClient.game.client.chatListener;

import android.util.Log;

import com.belafon.world.mobileClient.client.Client;
import com.belafon.world.mobileClient.game.Game;
import com.belafon.world.mobileClient.game.Fragments;
import com.belafon.world.mobileClient.game.Stats;
import com.belafon.world.mobileClient.gameActivity.WaitingScreenFragment;

/**
 * Listens to the messages from the server.
 */
public class ChatListener {
    private static final String TAG = "ChatListener";
    private Stats stats = Game.stats;
    private Fragments fragments;

    public void setFragments(Fragments fragments) {
        this.fragments = fragments;
    }
    /**
     * Decompose the string message from the server.
     * 
     * @param message is get from the server.
     */
    public synchronized void listen(String message) {
        String[] args = message.split(" ");

        try {
            listenBase(args);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void listenBase(String[] args) {
        if (!args[0].equals("server") && Client.disconnected) {
            Log.d(TAG, "listenBase: fragments and stats are not set while game message received");
            return;
        }

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
            case "add_item_in_sight" -> stats.visibles.addItem(args, fragments, stats.behaviours);
            case "add_creature_in_sight" -> stats.visibles.addCreature(args, fragments, stats.behaviours);
            case "add_resource_in_sight" -> stats.visibles.addResource(args, fragments, stats.behaviours);
            case "remove_item_in_sight" -> stats.visibles.removeItem(args, fragments);
            case "remove_creature_in_sight" -> stats.visibles.removeCreature(args, fragments);
            case "remove_resource_in_sight" -> stats.visibles.removeResource(args, fragments);
            case "new_resource_type" -> stats.visibles.addNewResourceType(args, stats.behaviours);
        }

    }

    private void listenBehaviour(String[] args) {
        switch (args[1]) {
            case "setupBehaviour" -> stats.behaviours.setupNewBehaviour(args);
            case "setupRequirement" -> stats.behaviours.setUpNewRequirement(args);
            case "doBehaviour" -> stats.behaviours.doBehaviour(args, stats, fragments);
        }
    }

    private void listenMap(String[] args) {
        switch (args[1]) {
            case "look_arround" -> stats.maps.lookAroundSurroundingPlaces(args, stats, fragments);
            case "remove_place_in_sight" -> stats.maps.removePlaceInSight(args, stats, fragments);
            case "new_map" -> stats.maps.addMap(args);
            case "weather" -> stats.maps.weather.setWeather(args);
            case "clouds" -> stats.maps.weather.setClouds(args);
            case "partOfDay" -> stats.maps.weather.setPartOfDay(args);
        }
    }

    private void listenPlayer(String[] args) {
        switch (args[1]) {
            case "actualCondition" -> listenActualCondition(args);
            case "abilityCondition" -> listenAbilityCondition(args);
            case "knowledge" -> listenKnowledge(args);
            case "feasibleBehaviour" -> feasibleBehaviourUpdate(args);
            case "currentPositionInfo" -> stats.maps.setCurrentPositionInfo(args, stats, fragments);
        }
    }

    private void feasibleBehaviourUpdate(String[] args) {
        switch (args[2]) {
            case "add" -> stats.behaviours.addNewFeasibleBehaviour(args[3]);
            case "remove" -> stats.behaviours.removeFeasibleBehaviour(args[3]);
        }
    }

    private void listenAbilityCondition(String[] args) {
        switch (args[2]) {
            case "strength" -> stats.body.setStrengthAbility(args[3], fragments);
            case "agility" -> stats.body.setAgilityAbility(args[3], fragments);
            case "speed_of_run" -> stats.body.setSpeedOfRunAbility(args[3], fragments);
            case "speed_of_walk" -> stats.body.setSpeedOfWalkAbility(args[3], fragments);
            case "hearing" -> stats.body.setHearingAbility(args[3], fragments);
            case "observation" -> stats.body.setObservationAbility(args[3], fragments);
            case "vision" -> stats.body.setVisionAbility(args[3], fragments);
        }
        ;
    }

    private void listenActualCondition(String[] args) {
        switch (args[2]) {
            case "health" -> stats.body.setHealth(args[3], fragments);
            case "hunger" -> stats.body.setHunger(args[3], fragments);
            case "bleeding" -> stats.body.setBleeding(args[3], fragments);
            case "heat" -> stats.body.setHeat(args[3], fragments);
            case "fatigue_max" -> stats.body.setFatigueMax(args[3], fragments);
            case "current_energy_output" -> stats.body.setCurrentEnergyOutput(args[3], fragments);
        }
        
    }

    private void listenKnowledge(String[] args) {
    }

    private void listenItem(String[] args) {
        switch (args[1]) {
            case "addItem" -> stats.inventory.addItem(fragments,
                    Integer.parseInt(args[2]), args[3], args[4], Integer.parseInt(args[5]),
                    Integer.parseInt(args[6]), Integer.parseInt(args[7]),
                    args, stats.behaviours);
            case "removeItem" -> stats.inventory.removeItem(fragments, Integer.parseInt(args[2]));
        }
    }

    private void listenServer(String[] args) {
        switch (args[1]) {
            case "number_of_players_to_wait" -> WaitingScreenFragment.setNumberOfPlayersToWait(Integer.parseInt(args[2]));
            case "startGame" -> WaitingScreenFragment.startGame();
        }
    }
}
