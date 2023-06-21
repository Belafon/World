package com.world.pcclient.maps.playersPlacePanels;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * This panel displays a detailed information about
 * a place, that is currently selected by the player.
 */
public class PlayersPlaceInfoPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private List<Component> effectPanels = new ArrayList<>();

    public PlayersPlaceInfoPanel(String name, String description, List<PlayersPlaceEffect> placeEffects) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        nameLabel = new JLabel(name);
        descriptionLabel = new JLabel(description);

        add(nameLabel);
        add(descriptionLabel);

        add(Box.createVerticalStrut(10));

        createPlaceEffectsPanels(placeEffects);
    }

    /**
     * Information of this panels are updated.
     * Usually when the player selects another
     * place.
     * This panel displayes a detaild info about
     * currently selected place then.
     * @param typePlace
     * @param placeEffects
     */
    public void rewriteCurrentPlace(TypePlace typePlace, List<PlayersPlaceEffect> placeEffects) {
        SwingUtilities.invokeLater(() -> {
            nameLabel.setText(typePlace.name);
            descriptionLabel.setText(typePlace.description);
            createPlaceEffectsPanels(placeEffects);
        });
    }

    private void createPlaceEffectsPanels(List<PlayersPlaceEffect> placeEffects) {
        for (Component comp : effectPanels) {
            this.remove(comp);
        }
        effectPanels = new ArrayList<>();
        for (PlayersPlaceEffect effect : placeEffects) {
            add(effect.getInfoPanel());
            add(Box.createVerticalStrut(5));
            effectPanels.add(effect.getInfoPanel());
            effectPanels.add(Box.createVerticalStrut(5));
        }
    }
}
