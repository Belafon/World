package com.example.world.game.behaviours;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.world.R;
import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.example.world.game.visibles.creatures.PlayableCreature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BehavioursExecutorFragment extends Fragment {
    private int fragmentContainerId;
    private Fragment lastFragment;
    private Behaviour behaviour;

    public BehavioursExecutorFragment(int fragmentContainerId, Fragment lastFragment, Behaviour behaviour) {
        this.fragmentContainerId = fragmentContainerId;
        this.lastFragment = lastFragment;
        this.behaviour = behaviour;
    }

    private LinearLayout requirementsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_behaviours_executor, container, false);

        TextView name = rootView.findViewById(R.id.behaviour_name);
        TextView description = rootView.findViewById(R.id.behaviour_description);
        Button execute = rootView.findViewById(R.id.execute_button);
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

    private void executeBehaviour(Behaviour behaviour) {
        // Execute the behaviour
    }

    // set of all requiremnets of concrete behaviour, that is currently beeing
    // displayed
    public Map<Behaviour.BehavioursRequirementDetail, RequirementChooser> requiremntsChoosers = new HashMap<>();
    private Set<BehavioursPossibleIngredient> availableIngredients = new HashSet<>();

    private void setRequirementsPanel(Behaviour behaviour) {
        Set<BehavioursPossibleIngredient> availableIngredients = new HashSet<>(PlayableCreature.allIngredients);

        // we have to remove all ingredients, that are already selected from different
        // behaviour
        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (AutoCompleteTextView autoCompleteTextView : chooser.autoCompleteTextViews) { // BUG // BUG // BUG try
                                                                                              // if this works fine
                String selectedIngredientText = autoCompleteTextView.getText().toString();

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
                getSatisfiableIngredients(requirement, satisfiableIngredients);

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
                    requirementChooser.addNewIngredient(item);
                }
            }
        }

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (AutoCompleteTextView autoCompleteTextView : chooser.autoCompleteTextViews) {
                autoCompleteTextView.setAdapter(new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_dropdown_item_1line, new ArrayList<>(availableIngredients)));
            }
        }

        setAvailableIngredients(behaviour);

        drawList();
    }

    private void setAvailableIngredients(Behaviour behaviour) {
        for (Behaviour.BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.numOfConcreteIngredient != 0) {
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfiableIngredients(requirement, satisfableIngredients);
                requiremntsChoosers.get(requirement).setAvailableIngredients(satisfableIngredients);
            }
        }
    }

    private void getSatisfiableIngredients(Behaviour.BehavioursRequirementDetail detailedRequirement,
            Set<BehavioursPossibleIngredient> satisfableIngredients) {
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

        LinearLayout insidePanel = new LinearLayout(requireContext()); // Create a LinearLayout to hold the
                                                                       // AutoCompleteTextView components
        insidePanel.setOrientation(LinearLayout.VERTICAL); // Set orientation to vertical

        ScrollView scrollView = new ScrollView(requireContext());
        scrollView.addView(insidePanel);

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (AutoCompleteTextView autoCompleteTextView : chooser.autoCompleteTextViews) {
                autoCompleteTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                    private BehavioursPossibleIngredient lastSelected = (BehavioursPossibleIngredient) autoCompleteTextView
                            .getTag();

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String selectedIngredientText = s.toString();

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
                            availableIngredients.add(lastSelected);
                            lastSelected = selectedIngredient;

                            chooser.selectIngredient(selectedIngredient, autoCompleteTextView);

                            setAvailableIngredients(behaviour);

                            panel.requestLayout();
                            panel.invalidate();
                        }
                    }
                });

                insidePanel.addView(autoCompleteTextView);
            }
        }

        panel.addView(scrollView);
        panel.setMinimumHeight(90);

        return panel;
    }

    public Behaviour getCurrentlySelectedBehaviour() {
        return behaviour;
    }

}
