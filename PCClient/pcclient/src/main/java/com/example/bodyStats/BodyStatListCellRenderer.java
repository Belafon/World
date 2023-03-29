package com.example.bodyStats;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class BodyStatListCellRenderer extends JLabel implements ListCellRenderer<BodyStat> {

    public BodyStatListCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends BodyStat> list, BodyStat value,
            int index, boolean isSelected, boolean cellHasFocus) {
        // Set the text of the JLabel to the name and value of the BodyStat object
        setText(value.getName() + ": " + value.getValue());

        // Set the background color of the JLabel based on whether it is selected
        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
        }

        // Set the foreground color of the JLabel based on whether it is selected
        if (isSelected) {
            setForeground(list.getSelectionForeground());
        } else {
            setForeground(list.getForeground());
        }

        // Set the font of the JLabel
        setFont(new Font("Arial", Font.PLAIN, 12));

        // Return the JLabel
        return this;
    }
}