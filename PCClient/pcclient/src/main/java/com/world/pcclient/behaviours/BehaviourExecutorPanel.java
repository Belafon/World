package com.world.pcclient.behaviours;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.world.pcclient.App;
import com.world.pcclient.behaviours.Behaviour.BehavioursRequirementDetail;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.world.pcclient.visibles.creatures.PlayableCreature;

public class BehaviourExecutorPanel {
    private Behaviour behaviour;
    private final JPanel panel = new JPanel();
    private final JLabel name;
    private final JLabel description;
    private final JButton execute;
    private JPanel requirementsPanel;
    private JList<JComboBox<BehavioursPossibleIngredient>> comboBoxList = new JList<>();

    public BehaviourExecutorPanel() {
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        name = new JLabel();
        Font boldFont = name.getFont().deriveFont(Font.BOLD);
        name.setFont(boldFont);
        description = new JLabel();
        description.setMaximumSize(new Dimension(Integer.MAX_VALUE, description.getPreferredSize().height));
        execute = new JButton("Execute");
        execute.setPreferredSize(new Dimension(execute.getPreferredSize().width, 30));
        execute.setVisible(false); // Initially hide the button

        topPanel.add(name, BorderLayout.NORTH);
        topPanel.add(description, BorderLayout.CENTER);

        requirementsPanel = new JPanel();
        requirementsPanel.setLayout(new BorderLayout());

        JPanel spacerPanel = new JPanel(); // Spacer panel to control the maximum height
        spacerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));

        requirementsPanel.add(spacerPanel, BorderLayout.NORTH);
        requirementsPanel.add(execute, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(topPanel);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(requirementsPanel, BorderLayout.SOUTH);
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;

        name.setText(behaviour.name);
        description.setText(behaviour.description);
        
        execute.setVisible(true);
        execute.setToolTipText("Execute the behaviour.");
        execute.setEnabled(true);

        setRequirementsPanel(behaviour);

        panel.revalidate();
    }

    // set of all requiremnets of concrete behaviour, that is currently beeing
    // displayed
    public Map<BehavioursRequirementDetail, RequirementChooser> requiremntsChoosers = new HashMap<>();
    private Set<BehavioursPossibleIngredient> availableIngredients = new HashSet<>();

    private void setRequirementsPanel(Behaviour behaviour) {
        this.availableIngredients = PlayableCreature.allIngredients;
        for (BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.withConcreteIngredient) {

                // we need to get a set of ingredients, that satisfies to this requirement
                // we have to go through all ingredients and check if it satisfies to this
                // requirement. if yes, we have to check if it is in the availableIngredients
                // set
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfableIngredients(behaviour, satisfableIngredients);

                var requirementChooser = new RequirementChooser(requirement);
                requiremntsChoosers.put(requirement, requirementChooser);

                for (int i = 0; i < requirement.amount; i++) {
                    var item = satisfableIngredients.iterator().next();
                    if (item != null) {
                        satisfableIngredients.remove(item);
                        availableIngredients.remove(item);
                        requirementChooser.addNewIngredient(item);
                    } else {
                        // There should be error, if this happen,
                        // it means, that the behaviour should not be possible to execute
                        // so the behaviour should not be in the list of possible behaviours
                        // -> execute button should be unabled
                        execute.setEnabled(false);
                        execute.setToolTipText("Unable to execute the behaviour.");
                    }
                }
            }
        }

        
        for (BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.withConcreteIngredient) {
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfableIngredients(behaviour, satisfableIngredients);
            }
            requiremntsChoosers.get(requirement).setAvailableIngredients(availableIngredients);
        }

        
        drawList();
    }

    private JPanel drawList() {
        JPanel panel = requirementsPanel;
        panel.removeAll();

        comboBoxList = new JList<>();
        DefaultListModel<JComboBox<BehavioursPossibleIngredient>> listModel = new DefaultListModel<>();

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (JComboBox<BehavioursPossibleIngredient> comboBox : chooser.comboBoxes) {
                listModel.addElement(comboBox);
            }
        }

        comboBoxList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(comboBoxList);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(400, 300));


        return panel;
    }

    private void getSatisfableIngredients(Behaviour behaviour,
            Set<BehavioursPossibleIngredient> satisfableIngredients) {
        for (BehavioursPossibleIngredient ingredient : App.getCurrentPlayableCreature().allIngredients) {
            if (ingredient.behaviours.contains(behaviour)
                    && availableIngredients.contains(ingredient))
                // the ingredient is satifable
                satisfableIngredients.add(ingredient);
        }
    }

    private static class RequirementChooser {
        public BehavioursRequirementDetail requirement;
        private List<BehavioursPossibleIngredient> selectedIngredients = new ArrayList<>();
        private Set<BehavioursPossibleIngredient> availableIngredients;
        List<JComboBox<BehavioursPossibleIngredient>> comboBoxes = new ArrayList<>();

        public RequirementChooser(BehavioursRequirementDetail requirement) {
            this.requirement = requirement;
        }

        public void setAvailableIngredients(Set<BehavioursPossibleIngredient> availableIngredients) {
            this.availableIngredients = availableIngredients;
            fillComboBoxes();
        }

        /**
         * Creates new combo box and safe default selected item
         * 
         * @param ingredient
         */
        public void addNewIngredient(BehavioursPossibleIngredient ingredient) {
            selectedIngredients.add(ingredient);
            comboBoxes.add(new JComboBox<>());
        }

        private void fillComboBoxes() {
            if (availableIngredients == null)
                throw new Error("setAvailableIngredients is null!");

            int comboBoxIndex = 0;
            for (JComboBox<BehavioursPossibleIngredient> comboBox : comboBoxes) {
                comboBox.removeAllItems();

                for (BehavioursPossibleIngredient ingredient : availableIngredients) {
                    comboBox.addItem(ingredient); 
                }

                BehavioursPossibleIngredient selectedIngredient = selectedIngredients.get(comboBoxIndex);
                comboBox.addItem(selectedIngredient);
                comboBox.setSelectedItem(selectedIngredient);

                comboBoxIndex++;
            }
        }

        public void selectIngredient(BehavioursPossibleIngredient ingredient, int index) {
            selectedIngredients.set(index, ingredient);
            JComboBox<BehavioursPossibleIngredient> comboBox = comboBoxes.get(index);
            comboBox.setSelectedItem(ingredient);
        }

        private JComboBox<BehavioursPossibleIngredient> createComboBox() {
            JComboBox<BehavioursPossibleIngredient> comboBox = new JComboBox<>();
            // Set the available ingredients as the items in the combo box
            for (BehavioursPossibleIngredient ingredient : availableIngredients) {
                comboBox.addItem(ingredient);
            }
            return comboBox;
        }
    }

    public Component getPanel() {
        return panel;
    }
}
