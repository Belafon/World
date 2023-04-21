package com.belafon.settings;

import com.belafon.world.World;
import com.belafon.world.creatures.Creature;
import com.belafon.world.maps.place.Place;

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
