package com.world.pcclient.maps;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.world.pcclient.maps.playersPlacePanels.PlacePanel;
import com.world.pcclient.maps.playersPlacePanels.PlayersPlaceEffect;
import com.world.pcclient.maps.playersPlacePanels.PlayersPlaceInfoPanel;
import com.world.pcclient.maps.playersPlacePanels.TypePlace;

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

    public void updatePlace(TypePlace type, int x, int y, List<PlayersPlaceEffect> effects,
            MouseAdapter mouseAdapter) {
        places[x][y].update(x, y, type, effects, mouseAdapter);
    }

    public void setPlaceUnknown(int x, int y, PlayersPlaceInfoPanel infoPanel) {
        places[x][y].setUnknown(x, y, infoPanel);
    }

    public JComponent getComponent() {
        return panel;
    }

    public static MouseAdapter getOnPlaceSelected(PlayersPlaceInfoPanel infoPlacePanel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof PlacePanel panel) {
                    infoPlacePanel.rewriteCurrentPlace(panel.typePlace, panel.placeEffects);
                } else throw new UnsupportedOperationException("Try to select Place in Surrounding map, but the clicked object was not PlacePanel.");
            }
        };
    }

}
