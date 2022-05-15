package com.models;

import com.google.gson.annotations.SerializedName;

public enum UserType {
    @SerializedName("0")
    Student,
    @SerializedName("1")
    Admin,
    @SerializedName("2")
    Instructor
}
