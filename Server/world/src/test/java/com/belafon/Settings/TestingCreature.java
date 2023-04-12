package com.belafon.Settings;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Maps.Place.Place;

public class TestingCreature extends Creature {

    public TestingCreature(World game, String name, Place position, 
        String appearence, int weight) {
        super(game, name, position, appearence,
            TestingMessages.createMessageSender(), weight);
            
    }

    @Override
    protected void setInventory() {
    }
    
}
