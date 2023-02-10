package Game.Creatures.Behaviour;

import Game.World;
import Game.Creatures.Player;
import Game.Maps.Place.Place;
import likeliness.Dice;

public class PlayersLookAround {
	public static final int sizeOfView = 3;
	private int[][] placesChangedAltitudes = new int[7][7];
	private Place[][] visiblePlaces = new Place[7][7];
	
	/**
     * 
     * @param game
     * @param player
     * @param place
     * @return the map of places, which the player cen see
     */ 
	public static String look(World game, Player player, Place place) {
		 // sledov�n� okoln�ch pol�, a� vzd�lenost 2 okolo hr��e
		PlayersLookAround lookAround = new PlayersLookAround();
		
		// we will look to ring of places around the players place and get know visiable places
		// it goes from the smallest ring around plazers place to bigger rings
		for(int numberOfCircle = 1; numberOfCircle <= sizeOfView; numberOfCircle++) { 

			// we go through all of the places in the square
			for(int i = place.positionX - numberOfCircle; i <= place.positionX + numberOfCircle; i++) {
				for(int j = place.positionY - numberOfCircle; j <= place.positionY + numberOfCircle; j++) {
					
					// we have to pay attention to bouderies of map
					if(i >= 0 && j >= 0 && i < place.map.sizeX && j < place.map.sizeY) {
							
						// we need further coordinate
						int biggerCoordinate = Math.abs(j - place.positionY); 
						if(biggerCoordinate < Math.abs(i - place.positionX)) biggerCoordinate = Math.abs(i - place.positionX);
					
						// we have to skip all places inside the ring
						if(biggerCoordinate == numberOfCircle) {
								int xCoordinateOfObstructedPlace = 0;
								int yCoordinateOfObstructedPlace = 0;
							
							// this will get coordinates of place, which could stand in the view of player, so
							// the player can't se this place (according the altitude of places)
							if(biggerCoordinate == Math.abs(j - place.positionY)) {
								if(j - place.positionY > 0)yCoordinateOfObstructedPlace = j - place.positionY - 1;
								else if(j - place.positionY < 0) yCoordinateOfObstructedPlace = j - place.positionY + 1;
							} else yCoordinateOfObstructedPlace =  Math.abs(j - place.positionY);

							if(biggerCoordinate == Math.abs(i - place.positionX)) {
								if(i - place.positionX > 0)xCoordinateOfObstructedPlace = i - place.positionX -1;
								else if(i - place.positionX < 0) xCoordinateOfObstructedPlace = i - place.positionX + 1;
							} else xCoordinateOfObstructedPlace =  Math.abs(i - place.positionX);
							
							// the smallest ring the players allways see, there is nothing else between the player and the place
							// +sizeOfView because, the player sees +sizeOfView circle places around him
							// [3,3] is actual position of player
							if(numberOfCircle == 1) {
								lookAround.visiblePlaces[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = place.map.places[i][j];
								lookAround.placesChangedAltitudes[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = place.map.places[i][j].altitude;
							}else {
								if(place.map.places[i][j].altitude > lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]
										|| place.altitude > lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]) {
									// player sees
									lookAround.placesChangedAltitudes[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = place.map.places[i][j].altitude;
									lookAround.visiblePlaces[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = place.map.places[i][j];
								//	System.out.println("cool - " + game.mainMap.map[i][j].getAltitude());
								} else {
									// player doesn't see
									// relative altitude = altitude of higher place + altitude of higher place / 10 * distance from the player
									lookAround.placesChangedAltitudes[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = 
											lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]
													+ 	(((lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView] / 10) * numberOfCircle));
									lookAround.visiblePlaces[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = null;
								}
							}
						}
					} else {
						// place out of the map ->
						lookAround.visiblePlaces[i - place.positionX + sizeOfView][j - place.positionY + sizeOfView] = null;
					}		
				}
			}
		}
		return makeMessage(lookAround, player);
	}

	// create message for one place
	private static String makeMessage(PlayersLookAround lookAround, Player player) {
		StringBuilder look = new StringBuilder("");
		int numberOfNullInRow = 0;
		for(int i  = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(lookAround.visiblePlaces[i][j] != null) {
					if(numberOfNullInRow > 0) { // data compress dat, when there is more null places in row
						look.append("x " + numberOfNullInRow + " ; ");
						numberOfNullInRow = 0;
					}
					look.append(getView(lookAround.visiblePlaces[i][j], player));
				} else {
					numberOfNullInRow++;
				}
			}
		}
		if(numberOfNullInRow > 0) // data compress dat, when there is more null places in row
			look.append("x " + numberOfNullInRow + " ; ");
		return look.toString();
	}

	// info about distant place
	private static StringBuilder getView(Place place, Player player) {
		StringBuilder message = new StringBuilder(place.typeOfPlace.name.name());
		for(int i = 0; i < place.effects.size(); i++) {
			int visibility = place.effects.get(i).visibility;
			if(visibility <= 200 && new Dice(visibility).toss() > 20)
				message.append(" " + place.effects.get(i).name);
		}
		message.append(" ; ");
		return message;
	}

}
