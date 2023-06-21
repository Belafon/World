package com.world.pcclient.maps.playersPlacePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.world.pcclient.maps.Place;
import com.world.pcclient.maps.SurroundingMap;

public class PlacePanel extends JPanel{
    public int positionX;
    public int positionY;
    private static final int SIZE_IN_PIXELS = 20;
    private Place place;

    public PlacePanel(int positionX, int positionY, PlayersPlaceInfoPanel panel) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.setPreferredSize(new Dimension(SIZE_IN_PIXELS, SIZE_IN_PIXELS));
        setUnknown(positionX, positionY, panel);
    }

    /**
     * Makes this Place panel to unknown place.
     * @param positionX
     * @param positionY
     * @param panel
     */
    public void setUnknown(int positionX, int positionY, PlayersPlaceInfoPanel panel) {
        Place.class.getName();
        update(Place.UNKNOWN, SurroundingMap.getOnPlaceSelected(panel));
    }

    /**
     * Updates all stats that are held by this panel.
     * @param place
     */
    public void update(Place place, MouseListener mouseListener) {
        this.place = place;
        SwingUtilities.invokeLater(() -> {
            this.setToolTipText(place.typePlace.name);
            this.setBackground(place.typePlace.backgroundColor);
            this.addMouseListener(mouseListener);
        });
    }

    /**
     * @return a panel with all detailed informations about the place.
     */
    public PlayersPlaceInfoPanel getInfoPanel() {
        return new PlayersPlaceInfoPanel(place.typePlace.name, place.typePlace.description, place.placeEffects);
    }

    /**
     * Checks if the place is also the players position.
     */
    public void isPlayersPlace() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    }

    public Place getPlace() {
        return place;
    }

}
