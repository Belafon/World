package com.belafon.world.maps;

import com.belafon.world.World;
import com.belafon.world.maps.mapGenerators.BasicGenerator;
import com.belafon.world.maps.place.TypeOfPlace;
import com.belafon.world.maps.place.UnboundedPlace;
import com.belafon.world.visibles.creatures.behaviour.BehavioursPossibleIngredientID;

public class Maps {
    public static final UnboundedPlace EMPTY_PLACE = new UnboundedPlace(TypeOfPlace.EMPTY, null) {
        @Override
        public int getTemperature() {
            return 0;
        }

        @Override
        public BehavioursPossibleIngredientID getBehavioursPossibleIngredientID() {
            return super.getBehavioursPossibleIngredientID();
        }

    };

    public Map[] maps;

    public Maps(World game) {
        maps = new Map[] {
                new Map(10, 10, new BasicGenerator(), 0, 36, 1000f, game)
        };
    }
}
