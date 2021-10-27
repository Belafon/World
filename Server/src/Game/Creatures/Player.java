package Game.Creatures;

import Game.Game;
import Game.Creatures.Inventory.Inventory;
import Game.Creatures.Inventory.PlayersGear;
import Game.Items.ListOfAllItemTypes;
import Game.Items.ItemsSpecialStats.SpecialFoodsProperties;
import Game.Maps.Place.Place;
import Server.Client;
import Game.Items.Types.Food;
import Game.Items.ListOfAllItemTypes.NamesOfFoodItemTypes;


public class Player extends Creature{
    public final Client client;
    public volatile boolean isReady = false; 
    public Player(Client client, Game game, String name, Place position, int id, String appearence){
        super(game, name, position, id, appearence, client.writer);
        this.client = client;
        //super(position, client.name, game, id);
    	setAbilityCondition(0, 0, 300, 600, 100, 0, 100);
        setInventory();
        
    }
	@Override
	protected void setInventory() {
		inventory = new Inventory(new PlayersGear(), this);
	}
    public void gameStart() {
        inventory.addItem(new Food(game, 0, 100, new SpecialFoodsProperties[]{}, ListOfAllItemTypes.foodTypes.get(NamesOfFoodItemTypes.Apple)));
        client.writer.surrounding.setInfoAboutSurrounding(position);
        //setBehaviour(new Move(game, this, game.maps.maps[0].places[1][1]));
    }
}
