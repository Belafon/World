package com.example.visibles.items;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import com.example.visibles.VisiblePanel;
import com.example.visibles.VisiblesListPanel;

public class ItemsPanel extends VisiblesListPanel{
    private ItemInfoPanel concreteInfoPanel;

    public ItemsPanel() {
        super("ItemVisibles");

        concreteInfoPanel = new ItemInfoPanel();
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
        concreteInfoPanel.update((Item) selectedVisiblePanel.getVisible()); // update item info panel with selected item
    }

    private static final class ItemInfoPanel extends JPanel {
        private JLabel name = new JLabel();
        private JLabel description = new JLabel();
        private JLabel id = new JLabel();
        private JLabel weight = new JLabel();
        private JLabel visibility = new JLabel();
        private JLabel toss = new JLabel();

        public ItemInfoPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // arrange components in a vertical column
            setBackground(Color.WHITE); // set background color of panel
            name.setBackground(new Color(217, 203, 116)); // set background color of label
            description.setBackground(new Color(147, 212, 214));
            id.setBackground(new Color(245, 163, 106));
            weight.setBackground(new Color(196, 233, 160));
            visibility.setBackground(new Color(252, 175, 198));
            toss.setBackground(new Color(203, 180, 223));

            // add the labels to the panel
            add(name);
            add(description);
            add(id);
            add(weight);
            add(visibility);
            add(toss);
        }

        public void update(Item item) {
            name.setText("Name: " + item.name);
            description.setText("Description: " + item.getDescription());
            id.setText("ID: " + item.getId());
            weight.setText("Weight: " + item.getWeight());
            visibility.setText("Visibility: " + item.getVisiblity());
            toss.setText("Toss: " + item.getToss());
        }
    }
}
