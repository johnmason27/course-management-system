package com.models;

import java.util.UUID;

public class Module {
    private UUID id;
    private String name;
    private boolean availability;
    private int level;

    public Module(UUID id, String name, boolean availability, int level) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.level = level;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
