package com.example.bodyStats;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JList;
import javax.swing.SwingUtilities;

public class BodyStat {
    private final String name;
    private String value;
    private JList<BodyStat> list;

    public BodyStat(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setList(JList<BodyStat> list) {
        this.list = list;
    }
}
