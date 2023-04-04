package com.example.maps.playersPlacePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.example.maps.SurroundingMap;

public class PlacePanel extends JPanel{
    public int positionX;
    public int positionY;
    private static final int SIZE_IN_PIXELS = 20;
    public TypePlace typePlace;
    public List<PlayersPlaceEffect> placeEffects;
    public PlacePanel(int positionX, int positionY, PlayersPlaceInfoPanel panel) {
        this.setPreferredSize(new Dimension(SIZE_IN_PIXELS, SIZE_IN_PIXELS));
        setUnknown(positionX, positionY, panel);
    }

    public void setUnknown(int positionX, int positionY, PlayersPlaceInfoPanel panel) {
        update(positionX, positionY, TypePlace.allTypes.get("unknown"),
            new ArrayList<>(), SurroundingMap.getOnPlaceSelected(panel));
    }

    public void update(int positionX, int positionY, TypePlace typePlace,
            List<PlayersPlaceEffect> effects, MouseAdapter mouseAdapter) {
        this.placeEffects = effects;
        this.typePlace = typePlace;
        this.positionX = positionX;
        this.positionY = positionY;
        final JPanel panel = this;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.setToolTipText(typePlace.name);
                panel.setBackground(typePlace.backgroundColor);
                panel.addMouseListener(mouseAdapter);    
            }
        });

    }

    public PlayersPlaceInfoPanel getInfoPanel() {
        return new PlayersPlaceInfoPanel(typePlace.name, typePlace.description, placeEffects);
    }

    
    public void isPlayersPlace() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

}
