package com.example.maps;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.example.mainWindow.MainWindow;
import com.example.maps.playersPlacePanels.PlayersPlaceInfoPanel;

public class SurroundingPlacesPanel extends JPanel {

    public SurroundingPlacesPanel(JComponent surroundingPlacesComponent,
            PlayersPlaceInfoPanel placePanelInfoPanel) {
        MainWindow.setTitleBorder("Surrounding map", this);

        this.setLayout(new GridLayout(2, 1));
        this.add(surroundingPlacesComponent);
        if(placePanelInfoPanel != null)
            this.add(placePanelInfoPanel);
    }
}