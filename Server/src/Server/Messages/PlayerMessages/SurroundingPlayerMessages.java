package Server.Messages.PlayerMessages;

import Game.Maps.Place.Place;
import Game.Maps.Resources.Resource;
import Game.Maps.Weather.Weather;
import Server.Messages.SurroundingMessages;
import Server.SendMessage.SendMessagePlayer;
import Game.Creatures.Behaviour.PlayersLookAround;

public class SurroundingPlayerMessages  extends SurroundingMessages{
    public final SendMessagePlayer sendMessage;

    public SurroundingPlayerMessages(SendMessagePlayer sendMessage) {
        this.sendMessage = sendMessage;
    }
	@Override
    public void setPartOfDay(String partOfDay) {
		sendMessage.sendLetter("map partOfDay " + partOfDay);
    }
	@Override
    public void setWeather(Weather weather) {
		sendMessage.sendLetter("map weather " + weather.getWeather());
    }
	@Override
    public void setClouds(Place place) {
		sendMessage.sendLetter("map clouds " + place.map.sky.getWeather(place).getClouds());
    }
	@Override
    public void setPosition(Place position) {
		setInfoAboutPlace(position);
		setInfoAboutSurrounding(position);
	}
	@Override
	public void setInfoAboutPlace(Place place){
		// setInfoAboutPlace needs to be filled
	}
	@Override
	public void setInfoAboutSurrounding(Place position){
		setTypeOfPlaceInfoDrawableSound(position);
		setWeather(position.map.sky.getWeather(position.positionX, position.positionY)); 
		setClouds(position);
		setInfoAboutSurroundingLookAround(position);
		setResources(position); // items which u can find
		setItems(position);
	}
	@Override
    public void setResources(Place position) {
		String message = "surrounding resources ";
		for(Resource resource : position.resourcesSorted)
			if(resource.durationOfFinding == 0) message +=  resource.typeOfResourceOfTypeOfPlace.typeOfResource.name;
		else break;
		sendMessage.client.writer.sendLetter(message);
	}
	@Override
	public void setResource(Resource resource){
		sendMessage.client.writer.sendLetter("surrounding resource " + resource.typeOfResourceOfTypeOfPlace.typeOfResource.name);
	}
	@Override
	public void setResourceNotFound(Resource resource){
		sendMessage.client.writer.sendLetter("surrounding resourceNotFound " + resource.typeOfResourceOfTypeOfPlace.typeOfResource.name);
	}
	@Override
	public void setItems(Place position){
		// TODO fill setItems method
	}
	@Override
	public void setInfoAboutSurroundingLookAround(Place position) {
		String look = PlayersLookAround.look(sendMessage.client.actual_game, sendMessage.client.player, position);
		String message = "behaviour lookAround " +  look;
		sendMessage.client.writer.sendLetter(message);
	}
	@Override
	public void setTypeOfPlaceInfoDrawableSound(Place position) {
		sendMessage.sendLetter("soundDrawable " + position.music + " " + position.picture);
	}
}
