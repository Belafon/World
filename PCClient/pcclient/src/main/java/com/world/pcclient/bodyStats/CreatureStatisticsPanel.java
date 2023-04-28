package com.world.pcclient.bodyStats;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.world.pcclient.mainWindow.MainWindow;

public class CreatureStatisticsPanel extends JPanel {

    private JLabel actualHeadingLabel;
    private JLabel abilityHeadingLabel;

    public CreatureStatisticsPanel(BodyStats stats) {
        // Set the layout to GridBagLayout
        setLayout(new GridBagLayout());

        // Create a titled border for the panel
        MainWindow.setTitleBorder("Body stats", this);

        // Set up the GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Create the actual heading label
        actualHeadingLabel = new JLabel("Actual condition", SwingConstants.CENTER);
        actualHeadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(actualHeadingLabel, gbc);

        // Increment the y position in the GridBagConstraints object
        gbc.gridy++;

        stats.actualList = new JList<>(new BodyStat[] { stats.hunger, stats.fatigueMax, stats.heat, stats.bleeding });
        stats.actualList.setCellRenderer(new BodyStatListCellRenderer());
        stats.actualList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stats.actualList.setBackground(new Color(255, 255, 200));
        stats.actualList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(stats.actualList, gbc);

        // Increment the y position in the GridBagConstraints object
        gbc.gridy++;

        // Create the ability heading label
        abilityHeadingLabel = new JLabel("Ability condition", SwingConstants.CENTER);
        abilityHeadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(abilityHeadingLabel, gbc);

        // Increment the y position in the GridBagConstraints object
        gbc.gridy++;

        stats.abilityList = new JList<>(new BodyStat[] {
            stats.healthAbility, stats.strengthAbility, stats.agilityAbility, stats.speedOfWalkAbility,
            stats.speedOfRunAbility, stats.currentSpeedAbility, stats.hearingAbility, stats.observationAbility,
            stats.visionAbility, stats.loudnessAbility, stats.attentionAbility, stats.energyOutputAbility
        });
        
        stats.abilityList.setCellRenderer(new BodyStatListCellRenderer());
        stats.abilityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stats.abilityList.setBackground(new Color(200, 255, 255));
        stats.abilityList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(stats.abilityList, gbc);
    }
}