package com.belafon.Game.Maps.Place;

import com.belafon.likeliness.Dice;
import com.belafon.Game.Maps.Place.ListOfAllTypesOfPlaces.NamesOfTypesOfPlaces;
import com.belafon.Game.Maps.Resources.TypeOfResourceOfTypeOfPlace;

public class TypeOfPlace {
    public final NamesOfTypesOfPlaces name;
    private final String[] pictures;
    private final String[] music;
    private final int color;
    private final String look;
    public final TypeOfResourceOfTypeOfPlace[] typeOfResources;
    public final int[] altitudesOfPressence;
    public final int evaporationRate;

    public TypeOfPlace(NamesOfTypesOfPlaces name, String[] pictures, String[] music, int[] altitudesOfPressence,
            TypeOfResourceOfTypeOfPlace[] typeOfResources, int evaporationRate, int color, String look) {
        this.name = name;
        this.color = color;
        this.look = look;
        this.pictures = pictures;
        this.music = music;
        this.altitudesOfPressence = altitudesOfPressence;
        this.typeOfResources = typeOfResources;
        this.evaporationRate = evaporationRate;
    }

    public String getPicture() {
        if (pictures.length == 0)
            return "";
        Dice dice = new Dice(pictures.length);
        return pictures[dice.toss() - 1];
    }

    public String getMusic() {
        if (music.length == 0)
            return "";
        Dice dice = new Dice(music.length);
        return music[dice.toss() - 1];
    }

    public int getColor() {
        return color;
    }

    public String getLook() {
        return look;
    }
}
