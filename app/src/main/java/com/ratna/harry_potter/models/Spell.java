package com.ratna.harry_potter.models;

public class Spell {
    String id;
    String name;
    String description;

    public Spell(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
