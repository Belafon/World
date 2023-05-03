package com.world.pcclient.visibles.creatures;

import com.world.pcclient.visibles.VisiblePanel;
import com.world.pcclient.visibles.VisiblesListPanel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;


public class CreaturesPanel extends VisiblesListPanel{
    private CreatureInfoPanel concreteInfoPanel;

    public CreaturesPanel() {
        super("CreatureVisibles");

        concreteInfoPanel = new CreatureInfoPanel();
        panel.add(concreteInfoPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds title panel into the list,
     * The item is also displayed in the list.
     * @param visiblePanel
     */
    public void addVisibleTitlePanel(VisiblePanel visiblePanel) {
        addVisibleTitlePanel(visiblePanel, () -> this.selectVisiblePanel(visiblePanel));
    }

    /**
     * Changes the concrete panel with detailed info about 
     * one creature,
     * @param visiblePanel
     */
    public void selectVisiblePanel(VisiblePanel visiblePanel) {
        if (selectedVisiblePanel != null) {
            selectedVisiblePanel.getTitlePanel().setBackground(null);
            // reset background color of previously selected visiblePanel
        }
        selectedVisiblePanel = visiblePanel;
        selectedVisiblePanel.getTitlePanel().setBackground(new Color(180, 180, 180)); // set background color of selected visiblePanel
        concreteInfoPanel.update((Creature) selectedVisiblePanel.getVisible()); // update item info panel with selected item
    }

    private static final class CreatureInfoPanel extends JPanel {
        private JLabel name = new JLabel();
        private JLabel description = new JLabel();
        private JLabel id = new JLabel();
        private JLabel positionX = new JLabel();
        private JLabel positionY = new JLabel();

        public CreatureInfoPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arrange components in a vertical column
            setBackground(Color.lightGray); // set background color of panel
            name.setBackground(new Color(217, 203, 116)); // set background color of label
            description.setBackground(new Color(147, 212, 214));
            id.setBackground(new Color(245, 163, 106));

            // add the labels to the panel
            add(name);
            add(description);
            add(id);
        }

        public void update(Creature creature) {
            name.setText("Name: " + creature.name);
            id.setText("Id: " + creature.getId());
            description.setText("Description: " + creature.getLook());
            /* positionX.setText("Position x: " + creature.getPositionX());
            positionY.setText("Position y: " + creature.getPositionY()); */
        }
    }
}
