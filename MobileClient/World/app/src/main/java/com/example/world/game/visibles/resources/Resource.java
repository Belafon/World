package com.example.world.game.visibles;

import java.util.Set;

import com.example.world.game.behaviours.BehavioursRequirement;
import com.example.world.game.visibles.resources.ResourceTypes.ResourceType;

public class Resource extends Visible {
    private final int id;
    private final ResourceType type;
    private final int mass;
    public Resource(int id, ResourceType type, int mass, Set<BehavioursRequirement> requirements) {
        super(requirements, type.name());
        this.type = type;
        this.id = id;
        this.mass = mass;
        if(mass >= type.masses().length)
            throw new IllegalArgumentException(
                "Resource: unsupported mass number: " + mass);
    }

    @Override
    public Runnable getOnTitleClick() {
        return () -> {};
    }
    @Override
	public String getId() {
		return "" + id;
	}

    public String getDescription() {
        return type.description();
    }

    public String getMass() {
        return type.masses()[mass];
    }

    @Override
    public String getVisibleType() {
        return "Resource";
    }

    public String getName() {
        return type.name();
    }

}