package Game.Maps.Resources;

import java.util.ArrayList;

import Game.Creatures.Creature;

public class Resource {
    public volatile int durationOfFinding;
    public volatile ArrayList<Creature> creatureListKnowsAboutLocation = new ArrayList<Creature>();
    public final TypeOfResourceWithPressence typeOfResourceWithPressence;
    public Resource(TypeOfResourceWithPressence typeOfResource) {
        this.typeOfResourceWithPressence = typeOfResource;
    }
    public synchronized void removeMemoryOfCreaturesAboutLocation(){
        creatureListKnowsAboutLocation = new ArrayList<Creature>();
    }
}
