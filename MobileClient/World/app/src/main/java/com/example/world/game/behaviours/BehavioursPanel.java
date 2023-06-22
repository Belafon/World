package com.example.world.game.behaviours;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import com.example.world.game.mainWindow.MainWindow;

public class BehavioursPanel {
    public JPanel panel;
    public final JPanel panelWithList;
    public Map<Behaviour, JLabel> itemLabels;
    private BehaviourExecutorPanel executor = new BehaviourExecutorPanel(this);

    public BehavioursPanel() {
        panel = new JPanel();
        MainWindow.setTitleBorder("Behaviours", panel);
        panel.setLayout(new BorderLayout()); // Set BorderLayout for the main panel

        panelWithList = new JPanel();
        panelWithList.setLayout(new BoxLayout(panelWithList, BoxLayout.Y_AXIS));
        itemLabels = new HashMap<>();

        panelWithList.setPreferredSize(new Dimension(100, 200));
        JScrollPane scrollPane = new JScrollPane(panelWithList);

        // Create a JSplitPane with the panel and executorPanel
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, executor.getContentPanel());
        splitPane.setResizeWeight(0.5); // Set the initial resize weight for the split pane

        panel.add(splitPane, BorderLayout.CENTER); // Add the split pane to the center of the main panel

    }

    private Behaviour selectedItem;

    public void addItem(Behaviour item) {
        JLabel label = new JLabel(item.name);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Add a bottom border
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedItem = item;
                updateLabelColors();
                executor.setBehaviour(item);
            }
        });

        itemLabels.put(item, label);
        panelWithList.add(label);

        updateLabelWidths();
        
        panel.revalidate();
        panel.repaint();
    }

    private void updateLabelWidths() {
        int containerWidth = panelWithList.getWidth();
        for (JLabel label : itemLabels.values()) {
            label.setPreferredSize(new Dimension(containerWidth, label.getPreferredSize().height));
        }
    }

    private void updateLabelColors() {
        for (JLabel label : itemLabels.values()) {
            if (label.getText().equals(selectedItem.name)) {
                label.setForeground(Color.LIGHT_GRAY); // Highlight the selected item with red color
            } else {
                label.setForeground(Color.BLACK); // Reset other labels to black color
            }
        }
    }

    public void removeItem(Object item) {
        JLabel label = itemLabels.remove(item);
        if (label != null) {
            panelWithList.remove(label);
            panelWithList.revalidate();
            panel.repaint();
        }
    }

    public Component getPanel() {
        return panel;
    }

    public void update(Behaviours behaviours) {
        executor.update(behaviours);
        for (JLabel label : itemLabels.values()) {
            label.setEnabled(true);
        }
    }

    public void reupdateBehaviour() {
        executor.setBehaviour(executor.getCurrentlySelectedBehaviour());        
    }

}
