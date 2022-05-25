package com.models.interfaces;

import com.models.UserType;

import java.util.UUID;

/**
 * Definition of a user model.
 */
public interface IUser {
    /**
     * Get the id of the user.
     * @return Id of user
     */
    UUID getId();

    /**
     * Set the id of the user.
     * @param id Id of user to set
     */
    void setId(UUID id);

    /**
     * Get the usertype of the user.
     * @return The usertype
     */
    UserType getUserType();

    /**
     * Set the usertype of the user.
     * @param userType The type of user
     */
    void setUserType(UserType userType);

    /**
     * Get the forename of the user.
     * @return Users forename
     */
    String getForename();

    /**
     * Set the forename of the user.
     * @param forename New forename
     */
    void setForename(String forename);

    /**
     * Get the surname of the user.
     * @return Users surname
     */
    String getSurname();

    /**
     * Set the surname of the user.
     * @param surname New surname
     */
    void setSurname(String surname);

    /**
     * Get the email of the user.
     * @return Users email
     */
    String getEmail();

    /**
     * Set the email of the user.
     * @param email Users email
     */
    void setEmail(String email);

    /**
     * Get the password of the user.
     * @return Users password
     */
    String getPassword();

    /**
     * Set the password of the user.
     * @param password New password
     */
    void setPassword(String password);
}
