package com.example.world.game.bodyStats;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JList;
import javax.swing.SwingUtilities;

public class BodyStat {
    private final String name;
    private int value;
    private JList<BodyStat> list;

    public BodyStat(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the value of the stat and prepagates the change into the panel.
     * @param value
     * @throws NumberFormatException
     */
    public void setValue(String value) throws NumberFormatException {
        this.value = Integer.parseInt(value);
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {       
                    list.updateUI();
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value;
    }

    public void setList(JList<BodyStat> list) {
        this.list = list;
    }
}
