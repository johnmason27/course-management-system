package com.models.interfaces;

import java.util.UUID;

public interface IGrade {
    UUID getId();
    void setId(UUID id);
    UUID getModuleId();
    void setModuleId(UUID id);
    int getGrade();
    void setGrade(int grade);
}
