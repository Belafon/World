package com.example.visibles;

public class Resource extends Visible {
    final int id;
    public Resource(String name, int id) {
        super(name);
        this.id = id;
    }

    @Override
    public Runnable getOnTitleClick() {
        return () -> {};
    }

	public Integer getId() {
		return id;
	}

    public String getDescription() {
        return null;
    }

    public String getMass() {
        return null;
    }

}
