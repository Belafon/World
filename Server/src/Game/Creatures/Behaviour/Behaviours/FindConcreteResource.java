package Game.Creatures.Behaviour.Behaviours;

import Console.ConsolePrint;
import Game.Game;
import Game.Calendar.Events.EventBehaviour;
import Game.Creatures.Creature;
import Game.Creatures.Behaviour.Behaviour;
import Game.Maps.Resources.Resource;
import Game.Maps.Resources.TypeOfResource;

public class FindConcreteResource extends Behaviour{
    private final Resource resource;
    private final TypeOfResource typeOfResource;
    private boolean found = false;
    private int durationOfFinding; // -1 will not be found
    public FindConcreteResource(Game game, int duration, int bodyStrain, Creature creature, TypeOfResource typeResource) {
        super(game, duration, bodyStrain, creature);
        this.typeOfResource = typeResource;
        resource = creature.getPosition().resources.get(typeResource);
    }

    @Override
    public void execute() {
        // 0 -> finds instantly p=1, -1 -> cant find,  p=100/(x+100)  p=0.5 -> x=100
        durationOfFinding =  - creature.abilityCondition.getVision() + creature.getPosition().getVisibility(); // for example fog ;
        if(resource != null && resource.durationOfFinding != -1) {
            found = true;
            durationOfFinding += resource.durationOfFinding; // lower means I will find it earlier
            // TODO possiable extension gauss function a*e^(-(x-posun_doprava)/2*const^2), posun_doprava by the durationOfFinding
        } else durationOfFinding += creature.getPosition().getDurationOfFindingOfResourceWhichIsNotHere(typeOfResource); // duration of when Creature finds out that, there is nothing
        if(durationOfFinding < 0)durationOfFinding = 0;
        super.event = new EventBehaviour(game.time.getTime() + durationOfFinding, game, this);
        game.calendar.add(event);
    }

    @Override
    public void interrupt() {
        // TODO maybe save that the creature was finding that some time
        
    }

    @Override
    public void cease() {
        if(found){
            ConsolePrint.success("Resource was found!!!");
            resource.creatureListKnowsAboutLocation.add(creature);
            creature.writer.surrounding.setResource(resource);
        }else{
            ConsolePrint.success("Ressource was NOT found!!!");
        }
    }

    @Override
    public String canCreatureDoThis() {
        return null;
    }
    
}
