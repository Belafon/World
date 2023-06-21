package com.world.pcclient.maps.weather;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.world.pcclient.mainWindow.MainWindow;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WeatherPanel {
    private JPanel panel = new JPanel();
    private JLabel clouds = new JLabel("unknown");
    private JLabel weather = new JLabel("unknown");
    private JLabel partOfDay = new JLabel("unknown");

    public WeatherPanel() {
        panel.setBackground(Color.CYAN);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        MainWindow.setTitleBorder("Weather", panel);
        LastClicked lastClicked = new LastClicked();
        clouds.addMouseListener(new LabelMouseListener(lastClicked));
        weather.addMouseListener(new LabelMouseListener(lastClicked));
        partOfDay.addMouseListener(new LabelMouseListener(lastClicked));

        panel.add(clouds);
        panel.add(weather);
        panel.add(partOfDay);
    }

    /**
     * Updates the text describing clouds
     * @param clouds
     */
    public void setClouds(String clouds) {
        SwingUtilities.invokeLater(() -> {
            this.clouds.setText(clouds);
            panel.revalidate();
            panel.repaint();
        });
    }

    /**
     * Updates the text describing weather
     * @param weather
     */
    public void setWeather(String weather) {
        SwingUtilities.invokeLater(() -> {
            this.weather.setText(weather);
            panel.revalidate();
            panel.repaint();
        });

    }

    /**
     * Updates the text describing part of day
     * @param partOfDay
     */
    public void setPartOfDay(String partOfDay) {
        SwingUtilities.invokeLater(() -> {
            this.partOfDay.setText(partOfDay);
            panel.revalidate();
            panel.repaint();
        });

    }

    /**
     * @return the panel, which lists all informations 
     * about current weather. 
     */
    public JPanel getPanel() {
        return panel;
    }

    private static class LastClicked {
        public JLabel label = null;
    }
    
    private class LabelMouseListener extends MouseAdapter {
        private LastClicked lastClicked;

        public LabelMouseListener(LastClicked lastClicked) {
            this.lastClicked = lastClicked;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (lastClicked.label != label
                    || lastClicked.label == null) {
                if(lastClicked.label != null)
                    lastClicked.label.setForeground(Color.BLACK);
                label.setForeground(new Color(200, 80, 0));
                lastClicked.label = label;
            }
        }
    }
}
