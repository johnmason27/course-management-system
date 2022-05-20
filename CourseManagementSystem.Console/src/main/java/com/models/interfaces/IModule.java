package com.models.interfaces;

import com.models.Module;

import java.util.UUID;

public interface IModule {
    UUID getId();
    void setId(UUID id);
    String getName();
    void setName(String name);
    boolean getAvailability();
    void setAvailability(boolean availability);
    int getLevel();
    void setLevel(int level);
    void printModule(Module module);
    boolean getOptional();
    void setOptional(boolean isOptional);
    UUID getInstructor();
    void setInstructor(UUID instructor);
}
