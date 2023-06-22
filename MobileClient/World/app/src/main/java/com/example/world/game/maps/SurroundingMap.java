package com.example.world.game.maps;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.example.world.game.maps.playersPlacePanels.PlacePanel;
import com.example.world.game.maps.playersPlacePanels.PlayersPlaceInfoPanel;

public class SurroundingMap {
    public static final int NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS = 7;
    private static final int SIZE_OF_GAP_IN_PIXELS = 5;
    private PlacePanel[][] places = new PlacePanel[NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS][NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS];
    private final JScrollPane panel;

    public SurroundingMap(PlayersPlaceInfoPanel infoPanel) {
        JPanel innerPanel = new JPanel(new GridLayout(NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS,
                NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS, SIZE_OF_GAP_IN_PIXELS,
                SIZE_OF_GAP_IN_PIXELS));

        for (int x = 0; x < NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS; x++) {
            for (int y = 0; y < NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS; y++) {
                places[x][y] = new PlacePanel(x, y, infoPanel);
                innerPanel.add(places[x][y]);
            }
        }

        places[NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS / 2][NUMBER_OF_PLACES_IN_SIGHT_IN_ONE_AXIS / 2]
                .isPlayersPlace();

        panel = new JScrollPane(innerPanel);
    }

    public PlacePanel getPlacePanel(int x, int y) {
        return places[x][y];
    }

    public void updatePlace(int x, int y, Place place) {
        place.setPanel(places[x][y]);
        places[x][y].update(place, getOnPlaceSelected(places[x][y].getInfoPanel()));
    }

    public void setPlaceUnknown(int x, int y, PlayersPlaceInfoPanel infoPanel) {
        places[x][y].setUnknown(x, y, infoPanel);
    }

    public JComponent getComponent() {
        return panel;
    }

    /**
     * @param infoPlacePanel
     * @return a mouse adapter, that changes detailed info about a place 
     * to the info about given place.
     */
    public static MouseAdapter getOnPlaceSelected(PlayersPlaceInfoPanel infoPlacePanel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof PlacePanel placePanel) {
                    infoPlacePanel.rewriteCurrentPlace(placePanel.getPlace().typePlace, placePanel.getPlace().placeEffects);
                } else throw new UnsupportedOperationException("Try to select Place in Surrounding map, but the clicked object was not PlacePanel.");
            }
        };
    }

}
