package com.example.maps.playersPlacePanels;

import javax.swing.JPanel;

import com.example.maps.playersPlacePanels.PlayersPlaceEffect.PlaceEffectName;

import javax.swing.JLabel;
import javax.swing.BoxLayout;

/** 
 * Shows info about place effect in the concrete place
 */
public class PlaceEffectInfoPanel extends JPanel {

    public PlaceEffectInfoPanel(PlaceEffectName name, String look) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        JLabel nameLabel = new JLabel(name.toString());
        nameLabel.setFont(nameLabel.getFont().deriveFont(nameLabel.getFont().getStyle() | java.awt.Font.BOLD));
        
        JLabel lookLabel = new JLabel(look);
        
        add(nameLabel);
        add(lookLabel);
    }
    
}