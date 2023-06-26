package com.example.world.game.behaviours;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.example.world.game.Panels;
import com.example.world.game.Stats;
import com.example.world.game.behaviours.Behaviour.BehaviourBuilder;

public class Behaviours {
    public final Map<String, Behaviour> allBehaviors = new Hashtable<>();
    public final Map<String, BehavioursRequirement> allRequirements = new Hashtable<>();
    public final Set<Behaviour> feasibles = new HashSet<>();
    public BehavioursFragment listPanel;
    private Behaviour currentBehaviour = null;

    public void setBehavioursFragment(int fragmentContainerId) {
        listPanel = new BehavioursFragment(fragmentContainerId);
    }

    public BehavioursFragment getPanel() {
        return listPanel;
    }

    public void addNewFeasibleBehaviour(String behavioursName) {
        var behav = allBehaviors.get(behavioursName);
        if (behav == null)
            throw new IllegalArgumentException(
                    "Behaviours: addNewFeasibleBehaviour: behaviour name is unknown: " + behavioursName);
        feasibles.add(behav);
        listPanel.addItem(behav);
    }

    public void removeFeasibleBehaviour(String behavioursName) {
        var behav = allBehaviors.get(behavioursName);
        if (behav == null)
            throw new IllegalArgumentException(
                    "Behaviours: removeFeasibleBehaviour: behaviour name is unknown: " + behavioursName);
        feasibles.remove(behav);
        listPanel.removeItem(behav);
    }

    public void setupNewBehaviour(String[] args) {
        String idName = args[2];
        String name = args[3].replaceAll("_", " ");
        String description = args[4].replaceAll("_", " ");
        String[] requirementNames;
        if (args.length < 6)
            requirementNames = new String[0];
        else
            requirementNames = args[5].split(",");

        var behaviour = new BehaviourBuilder(idName);
        behaviour.setDescription(description);
        behaviour.setName(name);
        for (String requirementsName : requirementNames) {
            String[] detailedRequir = requirementsName.split("[|]");

            behaviour.addRequirement(
                    allRequirements.get(detailedRequir[0]), // id name
                    detailedRequir[1], // description
                    Integer.parseInt(detailedRequir[2]), // number of specific ingredients
                    Integer.parseInt(detailedRequir[3])); // number of general ingredients

        }
        allBehaviors.put(idName, behaviour.build());
    }

    public void setUpNewRequirement(String[] args) {
        String idName = args[2];
        String name = args[3].replaceAll("_", " ");
        allRequirements.put(idName, new BehavioursRequirement(idName, name));
    }

    public void doBehaviour(String[] args, Stats stats, Panels panels) {
        
        if (currentBehaviour != null)
            currentBehaviour.setDuration(-1);
        if (args[2].equals("null")) {
            currentBehaviour = null;
            panels.behaviours.reupdateBehaviour();
            return;
        }

        var behaviour = allBehaviors.get(args[2]);
        var duration = Integer.parseInt(args[3]);

        if (duration == 0)
            panels.behaviours.reupdateBehaviour();


        if (behaviour == null)
            throw new IllegalArgumentException("Behaviours: doBehaviour: behaviour name is unknown: " + args[3]);

        if (duration != 0)
            currentBehaviour = behaviour;

        behaviour.setDuration(duration);
    }

    public Behaviour getCurrentBehaviour() {
        return currentBehaviour;
    }
}