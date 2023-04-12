package com.example.visibles;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.example.mainWindow.MainWindow;

public abstract class VisiblesListPanel {
    protected JPanel panel;
    protected JPanel visiblesList;
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
        visiblesList.revalidate();
        visiblesList.repaint();
    }
}
