package com.world.pcclient.visibles.items;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;

import com.world.pcclient.visibles.VisiblePanel;
import com.world.pcclient.visibles.VisiblesListPanel;

public class ItemsPanel extends VisiblesListPanel{
    private ItemInfoPanel concreteInfoPanel;

    public ItemsPanel() {
        super("ItemVisibles");

        concreteInfoPanel = new ItemInfoPanel();
        panel.add(concreteInfoPanel, BorderLayout.SOUTH);
    }
    
    /**
     * adds a title panel into the list, 
     * the item is also displayed.
     * @param visiblePanel
     */
    public void addVisibleTitlePanel(VisiblePanel visiblePanel) {
        SwingUtilities.invokeLater(() -> {
            addVisibleTitlePanel(visiblePanel, () -> this.selectVisiblePanel(visiblePanel));
        });
    }

    /**
     * Show detailed info about the item
     * @param visiblePanel
     */
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
            setBackground(Color.lightGray); // set background color of panel

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
