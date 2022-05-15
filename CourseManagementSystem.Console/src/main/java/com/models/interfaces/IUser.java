package com.models.interfaces;

import com.models.UserType;

import java.util.UUID;

public interface IUser {
    UUID getId();
    void setId(UUID id);
    UserType getUserType();
    void setUserType(UserType userType);
    String getForename();
    void setForename(String forename);
    String getSurname();
    void setSurname(String surname);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
}
