package com;

import com.models.User;

public class Session {
    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User currentUser) {
        user = currentUser;
    }
}
