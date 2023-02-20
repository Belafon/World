package Game.Creatures.Behaviour.Behaviours;

import java.util.Arrays;

import Console.ConsolePrint;
import Game.World;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Maps.Place.Place;
import Game.Maps.Place.UnboundedPlace;
import Game.Maps.Resources.Resource;
import Game.Maps.Resources.TypeOfResource;
import Game.ObjectsMemory.ObjectsMemoryCell;
import Game.ObjectsMemory.Visible;

public class FindConcreteResource extends Behaviour {
    private final Resource resource;
    private final TypeOfResource typeOfResource;
    private boolean found = false;
    private int durationOfFinding; // -1 will not be found

    public FindConcreteResource(World game, int duration, int bodyStrain, Creature creature,
            TypeOfResource typeResource) {
        super(game, duration, bodyStrain, creature);
        this.typeOfResource = typeResource;
        resource = creature.getLocation().resources.get(typeResource);
    }

    @Override
    public void execute() {
        // 0 -> finds instantly p=1, -1 -> cant find,  p=100/(x+100)  p=0.5 -> x=100
        durationOfFinding = -creature.abilityCondition.getVision() + creature.getLocation().getVisibility(); // for example fog ;
        if (resource != null && resource.durationOfFinding != -1) {
            found = true;
            durationOfFinding += resource.durationOfFinding; // lower means I will find it earlier
            // TODO possiable extension gauss function a*e^(-(x-posun_doprava)/2*const^2), shift left by the durationOfFinding
        } else
            durationOfFinding += getDurationOfFindingOfResourceWhichIsNotHere(creature.getLocation(), typeOfResource); // duration of when Creature finds out that, there is nothing
        if (durationOfFinding < 0)
            durationOfFinding = 0;
        super.event = new EventBehaviour(game.time.getTime() + durationOfFinding, game, this);
        game.calendar.add(event);
    }

    @Override
    public void interrupt() {
        // TODO maybe save that the creature was finding that some time

    }

    @Override
    public void cease() {
        if (found) {
            ConsolePrint.success("Resource was found!!!");
            creature.memory.addVisibleObjectSpotted(new ObjectsMemoryCell<Visible>(creature.game.time.getTime(), resource));
            creature.writer.surrounding.setResource(resource);
        } else {
            ConsolePrint.success("Ressource was NOT found!!!");
        }
    }

    @Override
    public String canCreatureDoThis() {
        return null;
    }

    // TODO can not be called when addResource() is called
    public static int getDurationOfFindingOfResourceWhichIsNotHere(Place place, TypeOfResource typeOfResource) {
        int sum = 0;
        int i = 0;
        int anonymConspicuousnessOfFindingResource = typeOfResource.conspicuousness / 2; // amount of it is 50
        while (i < place.resourcesSorted.length) {
            if (place.resourcesSorted[i].getConspicuousness() <= anonymConspicuousnessOfFindingResource)
                break;
            sum += place.resourcesSorted[i].getConspicuousness();
            i++;
        }
        return sum - i * anonymConspicuousnessOfFindingResource;
    }

    public static synchronized void setResourcesDurationOfFinding(UnboundedPlace place, Resource resource) throws Exception {
        int lastIndex = Arrays.asList(place.resourcesSorted).indexOf(resource);
        if (lastIndex == -1)
            throw new Exception("Value exception");

        int newIndex = Place.binarySearchTheClosestLower(place.resourcesSorted, resource);

        if (lastIndex < newIndex) {
            Resource last = place.resourcesSorted[newIndex - 1];
            place.resourcesSorted[newIndex - 1] = resource;
            for (int i = newIndex - 2; i >= lastIndex; i--) {
                Resource actual = place.resourcesSorted[i];
                place.resourcesSorted[i] = last;
                last = actual;
            }
        } else if (newIndex < lastIndex) {
            Resource last = resource;
            for (int i = newIndex; i <= lastIndex - 1; i++) {
                Resource actual = place.resourcesSorted[i];
                place.resourcesSorted[i] = last;
                last = actual;
            }
            place.resourcesSorted[lastIndex] = last;
        } // else do nothing
    }

}

