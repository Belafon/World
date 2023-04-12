package com.belafon.Game.Creatures;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Inventory.Inventory;
import com.belafon.Game.Creatures.Inventory.PlayersGear;
import com.belafon.Game.Items.ListOfAllItemTypes;
import com.belafon.Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import com.belafon.Game.Maps.Map;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.Server.Client;
import com.belafon.Game.Items.Types.Food;
import com.belafon.Game.Items.ListOfAllItemTypes.NamesOfFoodItemTypes;


public class Player extends Creature {
    public final Client client;
    public volatile boolean isReady = false; 
    public Player(Client client, World game, String name, Place position, String appearence){
        super(game, name, position, appearence, client.writer.sender, 100); // TODO weight
        this.client = client;
        //super(position, client.name, game, id);
    	setAbilityCondition(0, 0, 300, 600, 100, 0, 100);
        sendInfoAboutMaps();
    }

    private void sendInfoAboutMaps() {
        for (Map map : game.maps.maps) {
            writer.surrounding.setNewMap(map, map.sizeX, map.sizeY);
        }
    }
    @Override
	protected void setInventory() {
		inventory = new Inventory(new PlayersGear(), this);
	}
    public void gameStart() {
        inventory.addItem(new Food(game, 5, 100, new SpecialFoodsProperties[] {},
                ListOfAllItemTypes.foodTypes.get(NamesOfFoodItemTypes.apple), position));
        
        client.writer.surrounding.setInfoAboutSurrounding(position);
        //setBehaviour(new Move(game, this, game.maps.maps[0].places[1][1]));
    }
}
