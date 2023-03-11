package com.belafon.Game.Maps.Resources;

import com.belafon.Game.Creatures.Behaviour.Behaviours.BehavioursPossibleRequirement;
import com.belafon.Game.Maps.PlaceEffects.PlaceEffect;

public class TypeOfResource extends BehavioursPossibleRequirement {
    // final stats
    public final ListOfAllTypesOfResources.NamesOfTypesOfResources name;
    public final PlaceEffect[] effects;
    
    // (in cz "nápadnoost"), than with more resources with heigher consp., to find the resource with less consicousness it takes more time (viz. speedOfFinding in Resource)   
    // differece of conspiciousness = nuber of ticks to wait to find it
    public final int conspicuousness;
    
    public TypeOfResource(ListOfAllTypesOfResources.NamesOfTypesOfResources name, PlaceEffect[] effects, int conspicuousness) {
        this.name = name;
        this.effects = effects;
        this.conspicuousness = conspicuousness;
    }
}
