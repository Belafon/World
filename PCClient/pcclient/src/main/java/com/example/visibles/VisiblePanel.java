package com.example.visibles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisiblePanel {
    private final Visible visible;

    private final JPanel titlePanel;

    public VisiblePanel(Visible visible) {
        this.visible = visible;

        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(new JLabel(visible.name), BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titlePanel.setPreferredSize(new Dimension(150, 20));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                visible.getOnTitleClick().run();
            }
        });

    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public Visible getVisible() {
        return visible;
    }
}
