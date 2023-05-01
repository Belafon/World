package com.belafon.world.visibles.creatures;

import com.belafon.server.Client;
import com.belafon.world.World;
import com.belafon.world.maps.Map;
import com.belafon.world.maps.place.Place;
import com.belafon.world.visibles.creatures.inventory.Inventory;
import com.belafon.world.visibles.creatures.inventory.PlayersGear;
import com.belafon.world.visibles.items.ListOfAllItemTypes;
import com.belafon.world.visibles.items.ListOfAllItemTypes.NamesOfFoodItemTypes;
import com.belafon.world.visibles.items.itemsSpecialStats.SpecialFoodsProperties;
import com.belafon.world.visibles.items.types.Food;


public class Player extends Creature {
    public final Client client;
    public volatile boolean isReady = false; 

    public Player(Client client, World game, String name, Place position, String appearence) {
        super(game, name, position, appearence, client.writer.sender, 100); // TODO weight
        this.client = client;
        //super(position, client.name, game, id);
        setAbilityCondition(0, 0, 300, 600, 100, 0, 100);
        sendInfoAboutMaps();
    }

    /**
     * Sends info about all maps in the game to the client.
     * It sends ids and sizes only.
     */
    private void sendInfoAboutMaps() {
        for (Map map : game.maps.maps) {
            writer.surrounding.setNewMap(map, map.sizeX, map.sizeY);
        }
    }


    @Override
    protected void setInventory() {
        inventory = new Inventory(new PlayersGear(), this);
    }

    /**
     * This is called when new game is starting.
     */
    public void gameStart() {
        inventory.addItem(new Food(game, 5, 100, new SpecialFoodsProperties[] {},
                ListOfAllItemTypes.foodTypes.get(NamesOfFoodItemTypes.apple), position));
        
        client.writer.surrounding.setInfoAboutSurrounding(position);
        //setBehaviour(new Move(game, this, game.maps.maps[0].places[1][1]));
    }

    public boolean isDisconnected() {
        return client.isDisconnected();
    }
}
