package Game.Maps.Resources;

import java.util.ArrayList;

import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviours.FindConcreteResource;
import Game.Maps.Place.Place;
import Game.ObjectsMemory.Visible;

public class Resource implements Visible {
    public volatile int durationOfFinding;
    public final TypeOfResource typeOfResource;
    private volatile int amount; // 100 -> 100% of durationOfFinding, 100 -> regular amount

    public Resource(TypeOfResource typeOfResource, int amount) {
        this.typeOfResource = typeOfResource;
        this.amount = amount;
    }

    public int getConspicuousness(){
        return (typeOfResource.conspicuousness * amount) / 100;
    }

    public void setAmount(int amount, Place place) {
        this.amount = amount;
        try {
            FindConcreteResource.setResourcesDurationOfFinding(place, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Place getLocation() {
        return null;
    }
}
