package com.world.pcclient.behaviours;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.world.pcclient.behaviours.Behaviour.BehavioursRequirementDetail;
import com.world.pcclient.behaviours.behavioursPossibleIngredients.BehavioursPossibleIngredient;
import com.world.pcclient.visibles.creatures.PlayableCreature;

public class BehaviourExecutorPanel {
    private Behaviour behaviour;
    private final JPanel panel = new JPanel();
    private final JPanel contentPanel = new JPanel();
    private final JLabel name;
    private final JLabel description;
    private final JButton execute;
    private JPanel requirementsPanel;
    private BehavioursPanel behavioursPanel;

    public BehaviourExecutorPanel(BehavioursPanel behavioursPanel) {
        this.behavioursPanel = behavioursPanel;
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.0;

        name = new JLabel();
        Font boldFont = name.getFont().deriveFont(Font.BOLD);
        name.setFont(boldFont);
        contentPanel.add(name, constraints);

        constraints.gridy++;
        description = new JLabel();
        description.setMaximumSize(new Dimension(Integer.MAX_VALUE, description.getPreferredSize().height));
        contentPanel.add(description, constraints);

        constraints.gridy++;
        execute = new JButton("Execute");
        execute.setPreferredSize(new Dimension(execute.getPreferredSize().width, 20));
        execute.setVisible(false);
        contentPanel.add(execute, constraints);

        constraints.gridy++;
        constraints.weighty = 1.0;
        requirementsPanel = new JPanel(new BorderLayout());
        requirementsPanel.setPreferredSize(new Dimension(execute.getPreferredSize().width, 110));

        contentPanel.add(requirementsPanel, constraints);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(execute.getPreferredSize().width, 150));

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;

        name.setText(behaviour.name);
        description.setText(behaviour.description);

        execute.setVisible(true);
        execute.setToolTipText("Execute the behaviour.");
        execute.setEnabled(true);
        execute.removeActionListener(
                execute.getActionListeners().length == 0 ? null : execute.getActionListeners()[0]);
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execute.setEnabled(false);
                executeBehaviour(behaviour);
            }

        });
        setRequirementsPanel(behaviour);

        contentPanel.revalidate();
    }

    private void executeBehaviour(Behaviour behaviour) {
        // we have to get all selected ingredients
        List<BehavioursPossibleIngredient> selectedIngredients = new ArrayList<>();
        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (JComboBox<BehavioursPossibleIngredient> comboBox : chooser.comboBoxes) {
                selectedIngredients.add((BehavioursPossibleIngredient) comboBox.getSelectedItem());
            }
        }
        for (JLabel label : behavioursPanel.itemLabels.values()) {
            label.setEnabled(true);
        }
        behaviour.execute(selectedIngredients);
    }

    // set of all requiremnets of concrete behaviour, that is currently beeing
    // displayed
    public Map<BehavioursRequirementDetail, RequirementChooser> requiremntsChoosers = new HashMap<>();
    private Set<BehavioursPossibleIngredient> availableIngredients = new HashSet<>();

    private void setRequirementsPanel(Behaviour behaviour) {
        this.availableIngredients = new HashSet<>(PlayableCreature.allIngredients);

        // we have to remove all ingredients, that are already selected from different
        // behaviour
        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (JComboBox<BehavioursPossibleIngredient> comboBox : chooser.comboBoxes) {
                availableIngredients.add((BehavioursPossibleIngredient) comboBox.getSelectedItem());
            }
        }

        requiremntsChoosers = new HashMap<>();

        for (BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.numOfConcreteIngredient != 0) {

                // we need to get a set of ingredients, that satisfies to this requirement
                // we have to go through all ingredients and check if it satisfies to this
                // requirement. if yes, we have to check if it is in the
                // set
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfableIngredients(requirement, satisfableIngredients);

                var requirementChooser = new RequirementChooser(requirement);
                requiremntsChoosers.put(requirement, requirementChooser);

                for (int i = 0; i < requirement.numOfConcreteIngredient; i++) {
                    if (!satisfableIngredients.iterator().hasNext())
                        throw new Error("There is not enough satisfable ingredients for the behaviour. " +
                                "Behaviour: " + behaviour.name + ", requirement: " + requirement.requirement.name
                                + ".");

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

        setAvailableIngredients(behaviour);

        drawList();
    }

    private void setAvailableIngredients(Behaviour behaviour) {
        for (BehavioursRequirementDetail requirement : behaviour.requirements) {
            if (requirement.numOfConcreteIngredient != 0) {
                Set<BehavioursPossibleIngredient> satisfableIngredients = new HashSet<>();
                getSatisfableIngredients(requirement, satisfableIngredients);
                requiremntsChoosers.get(requirement).setAvailableIngredients(satisfableIngredients);
            }
        }
    }

    private static class ComboBoxItemModel extends DefaultComboBoxModel<BehavioursPossibleIngredient> {
        @Override
        public void setSelectedItem(Object anObject) {
            super.setSelectedItem(anObject);
        }
    }

    private JPanel drawList() {
        JPanel panel = requirementsPanel;
        panel.removeAll();

        JPanel insidePanel = new JPanel(); // Create a panel to hold the JComboBox components
        insidePanel.setLayout(new BoxLayout(insidePanel, BoxLayout.Y_AXIS)); // Use BoxLayout with vertical alignment

        JScrollPane scrollPane = new JScrollPane(insidePanel);

        for (RequirementChooser chooser : requiremntsChoosers.values()) {
            for (JComboBox<BehavioursPossibleIngredient> comboBox : chooser.comboBoxes) {
                comboBox.setPreferredSize(new Dimension(execute.getPreferredSize().width, 20));
                comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBox.getPreferredSize().height));
                comboBox.addActionListener(new ActionListener() {
                    private BehavioursPossibleIngredient lastSelected = (BehavioursPossibleIngredient) comboBox
                            .getSelectedItem();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        var selectedIngredient = (BehavioursPossibleIngredient) comboBox.getSelectedItem();
                        availableIngredients.remove(selectedIngredient);
                        availableIngredients.add(lastSelected);
                        lastSelected = selectedIngredient;

                        chooser.selectIngredient(selectedIngredient, comboBox);

                        setAvailableIngredients(behaviour);

                        panel.revalidate();
                        panel.repaint();
                    }
                });

                insidePanel.add(comboBox);
            }
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(execute.getPreferredSize().width, 90));

        return panel;
    }

    private void getSatisfableIngredients(BehavioursRequirementDetail detailedRequirement,
            Set<BehavioursPossibleIngredient> satisfableIngredients) {
        for (BehavioursPossibleIngredient ingredient : PlayableCreature.allIngredients) {
            if (ingredient.requirements.contains(detailedRequirement.requirement)
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
                ComboBoxItemModel comboBoxModel = new ComboBoxItemModel();
                comboBox.setEditable(false);

                for (BehavioursPossibleIngredient ingredient : availableIngredients) {
                    comboBoxModel.addElement(ingredient);
                }

                BehavioursPossibleIngredient selectedIngredient = selectedIngredients.get(comboBoxIndex);
                comboBoxModel.addElement(selectedIngredient);
                comboBoxModel.setSelectedItem(selectedIngredient);

                comboBox.setModel(comboBoxModel);

                comboBoxIndex++;
            }
        }

        public void selectIngredient(BehavioursPossibleIngredient ingredient,
                JComboBox<BehavioursPossibleIngredient> comboBox) {
            int index = comboBoxes.indexOf(comboBox);
            selectedIngredients.set(index, ingredient);
            comboBox.setSelectedItem(ingredient);
        }
    }

    public Component getContentPanel() {
        return panel;
    }

    public void update(Behaviours behaviours) {
        if (behavioursPanel.itemLabels.isEmpty()) {
            clearPanel();
        } else {
            if (behaviours.feasibles.contains(behaviour))
                setBehaviour(behaviour);
            else {
                clearPanel();
            }

            execute.setEnabled(true);
        }
    }

    private void clearPanel() {
        name.setText("");
        description.setText("");
        requirementsPanel.removeAll();
        execute.setEnabled(false);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public Behaviour getCurrentlySelectedBehaviour() {
        return behaviour;
    }
}
