package com.belafon.Game.Creatures.Behaviour;

import com.belafon.Game.World;
import com.belafon.Game.Creatures.Player;
import com.belafon.Game.Maps.Place.Place;
import com.belafon.likeliness.Dice;

public class PlayersLookAround {
    public static final int sizeOfView = 3;
    // the middle is the position of the viewer, "Relative map"
	private int[][] placesChangedAltitudes = new int[sizeOfView * 2 + 1][sizeOfView * 2 + 1];
	private Place[][] visiblePlaces = new Place[sizeOfView * 2 + 1][sizeOfView * 2 + 1];
	
	/**
     * 
     * @param game
     * @param player
     * @param viewersPosition
     * @return the map of places, which the player cen see
     */ 
	public static String look(World game, Player player, Place viewersPosition) {
		 // sledov�n� okoln�ch pol�, a� vzd�lenost 2 okolo hr��e
		PlayersLookAround lookAround = new PlayersLookAround();
		
		// we will look to ring of places around the players place and get know visiable places
		// it goes from the smallest ring around plazers place to bigger rings
		for(int numberOfCircle = 1; numberOfCircle <= sizeOfView; numberOfCircle++) { 

			// we go through all of the places in the square
			for(int x = viewersPosition.positionX - numberOfCircle; x <= viewersPosition.positionX + numberOfCircle; x++) {
                for (int y = viewersPosition.positionY - numberOfCircle; y <= viewersPosition.positionY
                        + numberOfCircle; y++) {
					
                    // cordinates of current place in relative map, squared map (size according sizeOfView), which middle point is viewers place 
                    int xInRelativeMap = x - viewersPosition.positionX + sizeOfView;
                    int yInRelativeMap = y - viewersPosition.positionY + sizeOfView;
                    
                    // we have to pay attention to bounderies of map
					if(x >= 0 && y >= 0 && x < viewersPosition.map.sizeX && y < viewersPosition.map.sizeY) {
							
						// we need further coordinate
						int biggerCoordinate = Math.abs(y - viewersPosition.positionY); 
                        if (biggerCoordinate < Math.abs(x - viewersPosition.positionX))
                            biggerCoordinate = Math.abs(x - viewersPosition.positionX);
					
						// we have to skip all places inside the ring
						if(biggerCoordinate == numberOfCircle) {
								int xCoordinateOfObstructedPlace = 0;
								int yCoordinateOfObstructedPlace = 0;
							
							// this will get coordinates of place, which could stand in the view of player, so
							// the player can't se this place (according the altitude of places)
							if(biggerCoordinate == Math.abs(y - viewersPosition.positionY)) {
								if(y - viewersPosition.positionY > 0)yCoordinateOfObstructedPlace = y - viewersPosition.positionY - 1;
								else if(y - viewersPosition.positionY < 0) yCoordinateOfObstructedPlace = y - viewersPosition.positionY + 1;
							} else yCoordinateOfObstructedPlace =  Math.abs(y - viewersPosition.positionY);

							if(biggerCoordinate == Math.abs(x - viewersPosition.positionX)) {
								if(x - viewersPosition.positionX > 0)xCoordinateOfObstructedPlace = x - viewersPosition.positionX -1;
								else if(x - viewersPosition.positionX < 0) xCoordinateOfObstructedPlace = x - viewersPosition.positionX + 1;
							} else xCoordinateOfObstructedPlace =  Math.abs(x - viewersPosition.positionX);
							
							// the smallest ring the players allways see, there is nothing else between the player and the place
							// +sizeOfView because, the player sees +sizeOfView circle places around him
							// [3,3] is actual position of viewer
							if(numberOfCircle == 1) {
								lookAround.visiblePlaces[xInRelativeMap][yInRelativeMap] = viewersPosition.map.places[x][y];
								lookAround.placesChangedAltitudes[xInRelativeMap][yInRelativeMap] = viewersPosition.map.places[x][y].altitude;
							}else {
								if(viewersPosition.map.places[x][y].altitude > lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]
										|| viewersPosition.altitude > lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]) {
									// player sees
									lookAround.placesChangedAltitudes[xInRelativeMap][yInRelativeMap] = viewersPosition.map.places[x][y].altitude;
									lookAround.visiblePlaces[xInRelativeMap][yInRelativeMap] = viewersPosition.map.places[x][y];
								} else {
									// player doesn't see
									// relative altitude = altitude of higher place + altitude of higher place / 10 * distance from the player
									lookAround.placesChangedAltitudes[xInRelativeMap][yInRelativeMap] = 
											lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView]
													+ 	(((lookAround.placesChangedAltitudes[xCoordinateOfObstructedPlace + sizeOfView][yCoordinateOfObstructedPlace + sizeOfView] / 10) * numberOfCircle));
									lookAround.visiblePlaces[xInRelativeMap][yInRelativeMap] = null;
								}
							}
						}
					} else {
						// place out of the map ->
						lookAround.visiblePlaces[xInRelativeMap][yInRelativeMap] = null;
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
