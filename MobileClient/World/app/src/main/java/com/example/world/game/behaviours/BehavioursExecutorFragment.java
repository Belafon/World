package com.example.world.game.behaviours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.world.R;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.example.world.game.visibles.creatures.PlayableCreature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BehavioursExecutorFragment extends Fragment {
    private int fragmentContainerId;
    private Fragment previousFragment;
    private Behaviour behaviour;

    public BehavioursExecutorFragment(int fragmentContainerId, Fragment lastFragment, Behaviour behaviour) {
        this.fragmentContainerId = fragmentContainerId;
        this.previousFragment = lastFragment;
        this.behaviour = behaviour;
    }

    private LinearLayout requirementsList;
    private TextView name;
    private TextView description;
    private Button execute;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_behaviours_executor, container, false);

        name = rootView.findViewById(R.id.behaviour_name);
        description = rootView.findViewById(R.id.behaviour_description);
        execute = rootView.findViewById(R.id.execute_button);
        requirementsList = rootView.findViewById(R.id.requirements_list);

        Button backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        name.setText(behaviour.name);
        description.setText(behaviour.description);

        execute.setVisibility(View.VISIBLE);
        execute.setText("Execute");
        execute.setEnabled(true);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute.setEnabled(false);
                executeBehaviour(behaviour);
            }
        });

        setRequirementsPanel(behaviour);

        return rootView;
    }

    public void setBehaviour(Behaviour behaviour, Behaviours behaviours) {
        if(!behaviours.feasibles.contains(behaviour)){
            goBack();
            return;
        }

        this.behaviour = behaviour;

        name.setText(behaviour.name);
        description.setText(behaviour.description);

        execute.setVisibility(View.VISIBLE);
        execute.setEnabled(true);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute.setEnabled(false);
                executeBehaviour(behaviour);
            }
        });

        setRequirementsPanel(behaviour);
    }

    private void executeBehaviour(Behaviour behaviour) {
        // Get all selected ingredients
        List<BehavioursPossibleIngredient> selectedIngredients = new ArrayList<>();
        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (Spinner spinner : chooser.spinners) {
                selectedIngredients.add((BehavioursPossibleIngredient) spinner.getSelectedItem());
            }
        }

        // Execute the behaviour with the selected ingredients
        behaviour.execute(selectedIngredients);
    }


    // set of all requiremnets of concrete behaviour, that is currently beeing
    // displayed
    public Map<Behaviour.BehavioursRequirementDetail, RequirementChooser> requiremntsChoosers = new HashMap<>();
    private Set<BehavioursPossibleIngredient> availableIngredients = new HashSet<>();

    private void setRequirementsPanel(Behaviour behaviour) {
        Set<BehavioursPossibleIngredient> availableIngredients;
        synchronized (PlayableCreature.allIngredients){
            availableIngredients = new HashSet<>(PlayableCreature.allIngredients);
        }

        // we have to remove all ingredients, that are already selected from different behaviour
        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (Spinner spinner : chooser.spinners) {
                // BUG // BUG try if this works fine
                String selectedIngredientText = spinner.getSelectedItem().toString();

                // Find the corresponding BehavioursPossibleIngredient object
                BehavioursPossibleIngredient selectedIngredient = null;
                for (BehavioursPossibleIngredient ingredient : availableIngredients) {
                    if (ingredient.toString().equals(selectedIngredientText)) {
                        selectedIngredient = ingredient;
                        break;
                    }
                }

                if (selectedIngredient != null) {
                    availableIngredients.remove(selectedIngredient);
                }
            }
        }


        requiremntsChoosers = new HashMap<>();

        for (Behaviour.BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.numOfConcreteIngredient != 0) {
                // Get the set of satisfiable ingredients for the requirement
                Set<BehavioursPossibleIngredient> satisfiableIngredients = new HashSet<>();
                getSatisfiableIngredients(requirement, satisfiableIngredients, availableIngredients);

                RequirementChooser requirementChooser = new RequirementChooser(requirement);
                requiremntsChoosers.put(requirement, requirementChooser);

                for (int i = 0; i < requirement.numOfConcreteIngredient; i++) {
                    if (satisfiableIngredients.isEmpty()) {
                        throw new Error("There are not enough satisfiable ingredients for the behaviour. " +
                                "Behaviour: " + behaviour.name + ", requirement: " + requirement.requirement.name
                                + ".");
                    }

                    BehavioursPossibleIngredient item = satisfiableIngredients.iterator().next();
                    satisfiableIngredients.remove(item);
                    availableIngredients.remove(item);
                    requirementChooser.addNewIngredient(item, getContext());
                }
            }
        }

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (Spinner spinner : chooser.spinners) {
                ArrayAdapter<BehavioursPossibleIngredient> adapter = new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_item, new ArrayList<>(availableIngredients));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        }

        setAvailableIngredients(behaviour, availableIngredients);

        drawList();
    }

    private void setAvailableIngredients(Behaviour behaviour, Set<BehavioursPossibleIngredient> availableIngredients) {
        this.availableIngredients = availableIngredients;
        for (Behaviour.BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.numOfConcreteIngredient != 0) {
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfiableIngredients(requirement, satisfableIngredients, this.availableIngredients);
                requiremntsChoosers.get(requirement).setAvailableIngredients(satisfableIngredients, this.getContext());
            }
        }
    }

    private void getSatisfiableIngredients(Behaviour.BehavioursRequirementDetail detailedRequirement,
                                           Set<BehavioursPossibleIngredient> satisfableIngredients, Set<BehavioursPossibleIngredient> availableIngredients) {
        for (BehavioursPossibleIngredient ingredient : PlayableCreature.allIngredients) {
            if (ingredient.requirements.contains(detailedRequirement.requirement)
                    && availableIngredients.contains(ingredient))
                // the ingredient is satifable
                satisfableIngredients.add(ingredient);
        }
    }

    private LinearLayout drawList() {
        LinearLayout panel = requirementsList;
        panel.removeAllViews();

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (Spinner spinner : chooser.spinners) {
                spinner.setBackground(getResources().getDrawable(R.color.black, null));
                spinner.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        100));

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    private BehavioursPossibleIngredient lastSelected = (BehavioursPossibleIngredient) spinner.getSelectedItem();
                    private boolean isUserCall = true;
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       // spinner.setOnItemSelectedListener(null);

                        BehavioursPossibleIngredient selectedIngredient = (BehavioursPossibleIngredient) parent.getItemAtPosition(position);

                        if(selectedIngredient == lastSelected
                                || !isUserCall){
                            isUserCall = true;
                            return;
                        }

                        isUserCall = false;


                        if (selectedIngredient != null) {
                            availableIngredients.remove(selectedIngredient);
                            availableIngredients.add(lastSelected);
                            lastSelected = selectedIngredient;

                            chooser.selectIngredient(selectedIngredient, spinner);

                            setAvailableIngredients(behaviour, availableIngredients);

                            panel.requestLayout();
                            panel.invalidate();
                        }

                     //   spinner.setOnItemSelectedListener(this);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                panel.addView(spinner);
            }
        }

        panel.setMinimumHeight(90);

        return panel;
    }

    private void goBack() {
        BehavioursFragment.EXECUTORS.remove(this);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, previousFragment);
        fragmentTransaction.commit();
    }

    public void update(Behaviours behaviours) {
        setBehaviour(behaviour, behaviours);
        execute.setEnabled(true);
    }

    public Behaviour getCurrentlySelectedBehaviour() {
        return behaviour;
    }
}
