package com.world.pcclient.behaviours;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.world.pcclient.behaviours.Behaviour.BehaviourBuilder;

public class Behaviours {
    public final Map<String, Behaviour> allBehaviors = new Hashtable<>();
    public final Map<String, BehavioursRequirement> allRequirements = new Hashtable<>();
    public final Set<Behaviour> feasibles = new HashSet<>();
    public final BehavioursPanel listPanel = new BehavioursPanel();

    public BehavioursPanel getPanel() {
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
                    "Behaviours: addNewFeasibleBehaviour: behaviour name is unknown: " + behavioursName);
        feasibles.remove(behav);
        listPanel.removeItem(behav);
    }

    public void setupNewBehaviour(String[] args) {
        String idName = args[2];
        String name = args[3].replaceAll("_", " ");
        String description = args[4].replaceAll("_", " ");
        String[] requirementNames;
        if(args.length < 6)
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
 
    public void setNewupRequirement(String[] args) {
        String idName = args[2];
        String name = args[3].replaceAll("_", " ");
        allRequirements.put(idName, new BehavioursRequirement(idName, name));
    }
}