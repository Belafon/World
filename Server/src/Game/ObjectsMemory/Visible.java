package Game.ObjectsMemory;

import Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import Game.Maps.Place.UnboundedPlace;

public interface Visible extends BehavioursPossibleIngredients{
    public UnboundedPlace getLocation();
}
