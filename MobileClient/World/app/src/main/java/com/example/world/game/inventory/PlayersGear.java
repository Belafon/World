package com.example.world.game.inventory;

import com.example.world.game.visibles.items.Item;
import com.example.world.game.visibles.items.Clothes;

public class PlayersGear {
    private Map<String, ClothesablePartOfBody> partOfBodies = new HashMap<>();

    {
        partOfBodies.put("Head", new ClothesablePartOfBody("Head"));
        partOfBodies.put("Shoulders", new ClothesablePartOfBody("Shoulders"));
        partOfBodies.put("Arms", new ClothesablePartOfBody("Arms"));
        partOfBodies.put("Hands", new ClothesablePartOfBody("Hands"));
        partOfBodies.put("Body", new ClothesablePartOfBody("Body"));
        partOfBodies.put("Legs", new ClothesablePartOfBody("Legs"));
        partOfBodies.put("Feet", new ClothesablePartOfBody("Feet"));
    }

    private volatile Item finger; // TODO
    private volatile Item neck;

    @Override
    public boolean putOn(Clothes clothes) {
        if(clothes.getPartOfBody().getClothes() != clothes)
            clothes.getPartOfBody().putOn(clothes);
    }

    @Override
    public boolean putOff(Clothes clothes) {
        if(clothes == clothes.getPartOfBody().getClothes())
            clothes.getPartOfBody().putOff(null);
    }

    public getPartOfBodyByName(String name) {
        return partOfBodies.get(name);
    }

}
