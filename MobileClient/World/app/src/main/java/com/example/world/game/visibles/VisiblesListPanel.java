package com.example.world.game.visibles;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Hashtable;

import com.example.world.game.mainWindow.MainWindow;

public abstract class VisiblesListPanel {
    protected JPanel panel;
    protected JPanel visiblesList;
    protected Hashtable<Visible, VisiblePanel> visiblePanels = new Hashtable<>();
    protected VisiblePanel selectedVisiblePanel;

    public VisiblesListPanel(String title) {
        this.panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(180, 220));
        MainWindow.setTitleBorder(title, panel);

        visiblesList = new JPanel();
        visiblesList.setLayout(new BoxLayout(visiblesList, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(visiblesList);
        scrollPane.setPreferredSize(new Dimension(200, 180));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // scroll without scrollbar displayied
        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();
                int currentValue = vScrollBar.getValue();
                int scrollAmount = vScrollBar.getBlockIncrement() * e.getWheelRotation();
                vScrollBar.setValue(currentValue + scrollAmount);
            }
        });
        panel.add(scrollPane, BorderLayout.NORTH);
    }

    /**
     * @return a panel that shows a list of visibles 
     * and under this list shows detailed info about
     * currently selected visible.
     */
    public JPanel getPanel() {
        return panel;
    }

    protected void addVisibleTitlePanel(VisiblePanel visiblePanel, Runnable selectVisiblePanel) {
        visiblePanel.getTitlePanel().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectVisiblePanel.run();
            }

        });
        visiblesList.add(visiblePanel.getTitlePanel());
        visiblePanels.put(visiblePanel.getVisible(), visiblePanel);
        visiblesList.revalidate();
        visiblesList.repaint();
    }

    protected void removeVisibleTitlePanel(Visible visible) {
        SwingUtilities.invokeLater(() -> {
            visiblesList.remove(visiblePanels.get(visible).getTitlePanel());
            visiblePanels.remove(visible);
            visiblesList.revalidate();
            visiblesList.repaint();
        });
    }
}

