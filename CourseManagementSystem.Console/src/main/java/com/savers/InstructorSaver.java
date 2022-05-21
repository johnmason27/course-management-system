package com.savers;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.io.IOManager;
import com.models.Instructor;

import java.util.ArrayList;

public class InstructorSaver implements ISaver<Instructor> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public void saveAll(ArrayList<Instructor> entities) {
        String stringEntities = this.gson.toJson(entities);
        this.ioManager.writeFile(FileNames.INSTRUCTORS, stringEntities);
    }
}
