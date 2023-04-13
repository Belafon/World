package com.example.visibles.resources;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import com.example.visibles.Resource;
import com.example.visibles.VisiblePanel;
import com.example.visibles.VisiblesListPanel;

public class ResourcesPanel extends VisiblesListPanel {
    private ResourceInfoPanel concreteInfoPanel;

    public ResourcesPanel() {
        super("ResourcesVisibles");

        concreteInfoPanel = new ResourceInfoPanel();
        panel.add(concreteInfoPanel, BorderLayout.SOUTH);
    }
    
    public void addVisibleTitlePanel(VisiblePanel visiblePanel) {
        addVisibleTitlePanel(visiblePanel, () -> this.selectVisiblePanel(visiblePanel));
    }

    public void selectVisiblePanel(VisiblePanel visiblePanel) {
        if (selectedVisiblePanel != null) {
            selectedVisiblePanel.getTitlePanel().setBackground(null); // reset background color of previously selected
                                                                      // visiblePanel
        }
        selectedVisiblePanel = visiblePanel;
        selectedVisiblePanel.getTitlePanel().setBackground(new Color(180, 180, 180)); // set background color of
                                                                                      // selected visiblePanel
        concreteInfoPanel.update((Resource) selectedVisiblePanel.getVisible()); // update item info panel with selected item
    }

    private static final class ResourceInfoPanel extends JPanel {
        private JLabel name = new JLabel();
        private JLabel description = new JLabel();
        private JLabel id = new JLabel();
        private JLabel mass = new JLabel();

        public ResourceInfoPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arrange components in a vertical column
            setBackground(Color.lightGray); // set background color of panel
            name.setBackground(new Color(217, 203, 116)); // set background color of label
            description.setBackground(new Color(147, 212, 214));
            id.setBackground(new Color(245, 163, 106));
            mass.setBackground(new Color(196, 233, 160));

            // add the labels to the panel
            add(name);
            add(description);
            add(id);
            add(mass);
        }

        public void update(Resource resource) {
            name.setText("Name: " + resource.name);
            description.setText("Description: " + resource.getDescription());
            id.setText("ID: " + resource.getId());
            mass.setText("Mass: " + resource.getMass());
        }
    }
}
