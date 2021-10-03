package Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Console.ConsolePrint;
import Game.Creatures.Behaviour.PlayersLookAround;
import Game.Items.Item;
import Game.Items.TypeItem.FoodTypeItem;
import Game.Items.Types.Clothes;
import Game.Items.Types.Food;
import Game.Items.Types.QuestItem;
import Game.Items.Types.Tool;
import Game.Maps.Place.Place;
import Game.Maps.Weather.Weather;

public class SendMessage {
	public PrintWriter output;
	private Client client;
	public Socket clientSocket;
	public SendMessage(Socket clientSocket, Client client) {
		this.clientSocket = clientSocket;
		this.client = client;
		try {
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
		} catch (IOException e) {
			ConsolePrint.error("SendMessage: Message ERROR in players connection :: " + client.name + " :: ->\n " + e);
			e.printStackTrace();
		}
	}
	
	public synchronized void sendLetter(String string) {
		ConsolePrint.message_to_player(string, client.name);
		output.println(string); // blank line between headers and content, very important !
		output.flush(); // flush character output stream buffer
	}

	public void setNumberOfPlayersInQueue(int number) {
		sendLetter("server number_of_players_to_wait " + number);
	}

    public void setPartOfDay(String partOfDay) {
		sendLetter("map partOfDay " + partOfDay);
    }

    public void setWeather(Weather weather) {
		sendLetter("map weather " + weather.getWeather());
    }

    public void setClouds(Place place) {
		sendLetter("map clouds " + place.map.sky.getWeather(place).getClouds());
    }

    public void setHealth(int health) {
		sendLetter("player actualCondition health " + health);
    }

    public void setHunger(int hunger) {
		sendLetter("player actualCondition hunger " + hunger);
    }

    public void setBleeding(int bleeding) {
		sendLetter("player actualCondition bleeding " + bleeding);
    }

    public void setHeat(int heat) {
		sendLetter("player actualCondition heat " + heat);
    }

    public void setFatigueMax(int fatigueMax) {
		sendLetter("player actualCondition fatigueMax " + fatigueMax);
    }

    public void setAddItem(Item item) {
		String nextValues = "";
		if(item instanceof Food){
			nextValues += ((Food)item).getFreshness() + " " + ((FoodTypeItem)item.type).getFilling() + " " + ((Food)item).getWarm();
		}else if(item instanceof Tool){
			nextValues += ((Tool)item).quality;
		}else if(item instanceof Clothes){

		}else if(item instanceof QuestItem){

		}
		sendLetter("item addItem " + item.id + " " + item.type.name + " " + item.type.weight + " " + item.type.visibility + " " + item.type.toss + " " + nextValues);
    }
	public void setRemoveItem(Item item) {
		sendLetter("item removeItem " + item.id + " " + item.type.name);
    }

    public void setPosition(Place position) {
		setInfoAboutPlace(position);
		setInfoAboutSurrounding(position);
	}
	public void setInfoAboutPlace(Place place){

	}
	public void setInfoAboutSurrounding(Place position){
		setTypeOfPlaceInfoDrawableSound(position);
		setWeather(position.map.sky.getWeather(position.positionX, position.positionY)); 
		setClouds(position);
		setInfoAboutSurroundingLookAround(position);
		setResourcesItems(position); // items which u can find

	}

    private void setResourcesItems(Place position) {
	}

	private void setInfoAboutSurroundingLookAround(Place position) {
		String look = PlayersLookAround.look(this.client.actual_game, this.client.player, position);
		String message = "behaviour lookAround " +  look;
		this.client.writer.sendLetter(message);
	}

	private void setTypeOfPlaceInfoDrawableSound(Place position) {
		sendLetter("soundDrawable " + position.music + " " + position.picture);
	}

	public void ClothesPutOn(Clothes clothes) {
		sendLetter("item " + clothes.getClass().getSimpleName() + " putOn " + clothes.id);
	}

    public void ClothesPutOff(Clothes clothes) {
		sendLetter("item " + clothes.getClass().getSimpleName() + " putOff " + clothes.id);

    }

    public void startGame() {
		sendLetter("server startGame");
    }

}
