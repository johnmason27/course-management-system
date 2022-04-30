package com.gui;

import com.models.User;
import com.models.Module;

import java.util.ArrayList;

public class Session {
    public static User user = null;
    public static ArrayList<Module> cachedModules = null;

    public static void setUser(User user) {
        Session.user = user;
    }
    public static void setCachedModules(ArrayList<Module> modules) {
        Session.cachedModules = modules;
    }
}
