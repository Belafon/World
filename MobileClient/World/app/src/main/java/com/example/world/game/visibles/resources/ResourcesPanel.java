package com.example.world.game.visibles.resources;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import com.example.world.game.visibles.Resource;
import com.example.world.game.visibles.VisiblePanel;
import com.example.world.game.visibles.VisiblesListPanel;

public class ResourcesPanel extends VisiblesListPanel {
    private ResourceInfoPanel concreteInfoPanel;

    public ResourcesPanel() {
        super("ResourcesVisibles");

        concreteInfoPanel = new ResourceInfoPanel();
        panel.add(concreteInfoPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Adds a title panel about a resource into
     * the list.
     * The item is also displayed;
     * @param visiblePanel 
     * */
    public void addVisibleTitlePanel(VisiblePanel visiblePanel) {
        addVisibleTitlePanel(visiblePanel, () -> this.selectVisiblePanel(visiblePanel));
    }

    /**
     * Show detailed info about the item
     * @param visiblePanel
     */
    public void selectVisiblePanel(VisiblePanel visiblePanel) {
        if (selectedVisiblePanel != null) {
            // reset background color of previously selected visiblePanel
            selectedVisiblePanel.getTitlePanel().setBackground(null); 
        }
        selectedVisiblePanel = visiblePanel;
        // set background color of selected visiblePanel
        selectedVisiblePanel.getTitlePanel().setBackground(new Color(180, 180, 180)); 
        // update item info panel with selected item
        concreteInfoPanel.update((Resource) selectedVisiblePanel.getVisible()); 
    }
    
    private static final class ResourceInfoPanel extends JPanel {
        private JLabel name = new JLabel();
        private JLabel description = new JLabel();
        private JLabel id = new JLabel();
        private JLabel mass = new JLabel();

        public ResourceInfoPanel() {
            // arrange components in a vertical column
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
            setBackground(Color.lightGray); 
            name.setBackground(new Color(217, 203, 116)); 
            description.setBackground(new Color(147, 212, 214));
            id.setBackground(new Color(245, 163, 106));
            mass.setBackground(new Color(196, 233, 160));

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
