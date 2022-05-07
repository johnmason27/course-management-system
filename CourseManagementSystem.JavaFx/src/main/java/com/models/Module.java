package com.models;

import java.util.UUID;

public class Module {
    private UUID id;
    private String moduleName;
    private boolean isModuleRunning;
    private int level;

    public Module(UUID id, String moduleName, boolean isModuleRunning, int level) {
        this.id = id;
        this.moduleName = moduleName;
        this.isModuleRunning = isModuleRunning;
        this.level = level;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean getIsModuleRunning() {
        return this.isModuleRunning;
    }

    public void setIsModuleRunning(boolean isModuleRunning) {
        this.isModuleRunning = isModuleRunning;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
