package com.world.pcclient.behaviours;

import com.world.pcclient.behaviours.Behaviours.BehavioursRequirementNames;

public class BehavioursRequirement {

    public final BehavioursRequirementNames tag;

    public final String name;

    public BehavioursRequirement(BehavioursRequirementNames tag, String name) {
        this.tag = tag;
        this.name = name;
    }
}
