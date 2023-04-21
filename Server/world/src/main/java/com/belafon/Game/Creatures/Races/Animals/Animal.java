package com.belafon.Game.Creatures.Races.Animals;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Creature;
import com.belafon.Game.Creatures.Inventory.Gear;
import com.belafon.Game.Creatures.Inventory.Inventory;
import com.belafon.Game.Creatures.Races.Animals.AnimalRaces.AnimalRace;
import com.belafon.Game.Items.Types.Clothes;
import com.belafon.Game.Maps.Place.UnboundedPlace;

public class Animal extends Creature {
    public final AnimalRace race;

    public Animal(World game, String name, UnboundedPlace position, String appearence,
            int weight, AnimalRace race) {
        super(game, name, position, appearence, race.sendMessage, weight);
        this.race = race;
    }

    @Override
    protected void setInventory() {
        inventory = new Inventory(new Gear() {

            @Override
            public boolean putOn(Clothes clothes) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'putOn'");
            }

            @Override
            public boolean putOff(Clothes clothes) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'putOff'");
            }
        }, this);
    }
}
