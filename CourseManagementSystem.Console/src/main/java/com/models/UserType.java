package com.models;

import com.google.gson.annotations.SerializedName;

/**
 * Definition of user types in the system.
 */
public enum UserType {
    /**
     * Student user type
     */
    @SerializedName("0")
    Student,
    /**
     * Admin user type
     */
    @SerializedName("1")
    Admin,
    /**
     * Instructor user type
     */
    @SerializedName("2")
    Instructor
}
