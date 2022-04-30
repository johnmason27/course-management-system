package com.models;

import java.util.UUID;

public class Module {
    private UUID id;
    private String moduleName;

    public Module(UUID id, String moduleName) {
        this.id = id;
        this.moduleName = moduleName;
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
}
