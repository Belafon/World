package com.example.world.game.behaviours;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.world.game.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequirementChooser {
    public Behaviour.BehavioursRequirementDetail requirement;
    private List<BehavioursPossibleIngredient> selectedIngredients = new ArrayList<>();
    private Set<BehavioursPossibleIngredient> availableIngredients;
    List<AutoCompleteTextView> autoCompleteTextViews = new ArrayList<>();

    public RequirementChooser(Behaviour.BehavioursRequirementDetail requirement) {
        this.requirement = requirement;
    }

    public void setAvailableIngredients(Set<BehavioursPossibleIngredient> availableIngredients) {
        this.availableIngredients = availableIngredients;
        fillAutoCompleteTextViews();
    }

    /**
     * Creates a new AutoCompleteTextView and saves the default selected item.
     *
     * @param ingredient The selected ingredient.
     */
    public void addNewIngredient(BehavioursPossibleIngredient ingredient) {
        selectedIngredients.add(ingredient);
        autoCompleteTextViews.add(new AutoCompleteTextView(null));
    }

    private void fillAutoCompleteTextViews() {
        if (availableIngredients == null)
            throw new Error("setAvailableIngredients is null!");

        int autoCompleteTextViewIndex = 0;
        for (AutoCompleteTextView autoCompleteTextView : autoCompleteTextViews) {
            List<BehavioursPossibleIngredient> ingredientsList = new ArrayList<>(availableIngredients);
            ArrayAdapter<BehavioursPossibleIngredient> adapter = new ArrayAdapter<>(null,
                    android.R.layout.simple_dropdown_item_1line, ingredientsList);
            autoCompleteTextView.setAdapter(adapter);

            BehavioursPossibleIngredient selectedIngredient = selectedIngredients.get(autoCompleteTextViewIndex);
            autoCompleteTextView.setText(selectedIngredient + "", false);

            autoCompleteTextViewIndex++;
        }
    }

    public void selectIngredient(BehavioursPossibleIngredient ingredient,
                                 AutoCompleteTextView autoCompleteTextView) {
        int index = autoCompleteTextViews.indexOf(autoCompleteTextView);
        selectedIngredients.set(index, ingredient);
        autoCompleteTextView.setText(ingredient + "", false);
    }
}
