package com.savers;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.io.IOManager;
import com.models.Admin;

import java.util.ArrayList;

public class AdminSaver implements ISaver<Admin> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public void saveAll(ArrayList<Admin> entities) {
        String stringEntities = this.gson.toJson(entities);
        this.ioManager.writeFile(FileNames.ADMINS, stringEntities);
    }
}
