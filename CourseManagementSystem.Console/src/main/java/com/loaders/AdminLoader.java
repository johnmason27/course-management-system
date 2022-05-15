package com.loaders;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import com.models.Admin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class AdminLoader implements ILoader<Admin> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public ArrayList<Admin> loadAll() {
        String adminsFileContents = this.ioManager.readFile(FileNames.Admins);
        Type adminListType = new TypeToken<ArrayList<Admin>>(){}.getType();
        ArrayList<Admin> admins = this.gson.fromJson(adminsFileContents, adminListType);

        if (admins == null) {
            return new ArrayList<>();
        }

        return admins;
    }

    public Admin find(String nameEmail) {
        return this.loadAll().stream()
                .filter(a -> a.getUsername().equals(nameEmail) || a.getEmail().equals(nameEmail))
                .findAny()
                .orElse(null);
    }

    public Admin find(UUID id) {
        return this.loadAll().stream()
                .filter(a -> a.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
