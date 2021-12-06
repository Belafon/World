package Game.Maps.Resources;

import likeliness.Dice;

public class TypeOfResourceWithPressence {
    public final int likelinessOfPressence; // 0 - 100, ...of the resource in specific place
    public final TypeOfResource typeOfResource;
    public TypeOfResourceWithPressence(int likelinessOfPressence, TypeOfResource typeOfResource) {
        this.likelinessOfPressence = likelinessOfPressence;
        this.typeOfResource = typeOfResource;
    }

    public boolean isHereResource(){
        Dice dice = new Dice(101);
        return dice.toss() - 1 <= likelinessOfPressence ? true : false; 
    }
}
