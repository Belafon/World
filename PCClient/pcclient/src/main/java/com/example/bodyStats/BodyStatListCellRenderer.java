package com.example.bodyStats;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Sets properties to Label of single BodyStat
 */
class BodyStatListCellRenderer extends JLabel implements ListCellRenderer<BodyStat> {

    public BodyStatListCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends BodyStat> list, BodyStat value,
            int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName() + ": " + value.getValue());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
        }

        if (isSelected) {
            setForeground(list.getSelectionForeground());
        } else {
            setForeground(list.getForeground());
        }

        setFont(new Font("Arial", Font.PLAIN, 12));

        return this;
    }
}