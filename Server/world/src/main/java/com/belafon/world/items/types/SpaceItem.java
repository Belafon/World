package com.belafon.world.items.types;

import com.belafon.world.World;
import com.belafon.world.items.Item;
import com.belafon.world.items.typeItem.TypeItem;
import com.belafon.world.maps.place.ItemPlace;
import com.belafon.world.maps.place.ListOfAllTypesOfPlaces;
import com.belafon.world.maps.place.UnboundedPlace;

public class SpaceItem extends Item {
    private UnboundedPlace space;

    private SpaceItem(World game, TypeItem type, UnboundedPlace position) {
        super(game, type, position);
    }
    
    public UnboundedPlace getSpace() {
        return space;
    }
    public void setSpace(UnboundedPlace space) {
        this.space = space;
    }
    
    public UnboundedPlace getSpacePlace() {
        return space;
    }

    @Override
    public int getWeight() {
        Integer sumOfItemsWeight = space.getItems((items) -> {
            return items.stream().mapToInt((x) -> x.getWeight()).sum();
        });

        Integer sumOfCreaturesWeight = space.getCreatures((creatures) -> {
            return creatures.stream().mapToInt((x) -> x.getWeight()).sum();
        });

        return super.getWeight() + sumOfItemsWeight + sumOfCreaturesWeight;
    }

    public static class BuilderSpaceItem {
        private final SpaceItem spaceItem;

        public BuilderSpaceItem(World game, TypeItem type, UnboundedPlace position) {
            spaceItem = new SpaceItem(game, type, position);
        }

        public SpaceItem build(World game) {
            spaceItem.space = new ItemPlace(
                ListOfAllTypesOfPlaces.typeOfPlaces.get(
                    ListOfAllTypesOfPlaces.NamesOfTypesOfPlaces.back_space),
                game,
                spaceItem);
            return spaceItem;
        }
    }
}
