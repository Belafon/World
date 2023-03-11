package com.belafon.Game.ObjectsMemory;

import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleIngredients;
import com.belafon.Game.Maps.Place.UnboundedPlace;

public interface Visible extends BehavioursPossibleIngredients{
    public UnboundedPlace getLocation();
}
