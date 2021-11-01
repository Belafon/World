package Game.Maps.Resources;

import java.util.ArrayList;

import Game.Creatures.Creature;
import Game.Maps.Place.Place;

public class Resource {
    public volatile int durationOfFinding;
    public volatile ArrayList<Creature> creatureListKnowsAboutLocation = new ArrayList<Creature>();
    public final TypeOfResourceOfTypeOfPlace typeOfResourceOfTypeOfPlace;
    private volatile int amount; // 100 -> 100% of durationOfFinding, 100 -> regular amount

    public Resource(TypeOfResourceOfTypeOfPlace typeOfResource, int amount) {
        this.typeOfResourceOfTypeOfPlace = typeOfResource;
        this.amount = amount;
    }
    public synchronized void removeMemoryOfCreaturesAboutLocation(){
        creatureListKnowsAboutLocation = new ArrayList<Creature>();
    }

    public int getConspicuousness(){
        return (typeOfResourceOfTypeOfPlace.typeOfResource.conspicuousness * amount) / 100;
    }

    public void setAmount(int amount, Place place){
        this.amount = amount;
        try {
            place.setResourcesDurationOfFinding(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
