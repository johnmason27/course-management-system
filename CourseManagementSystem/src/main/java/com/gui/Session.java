package com.gui;

import com.models.User;

public class Session {
    public static User user = null;

    public static void setUser(User user) {
        Session.user = user;
    }
}
