package com.savers;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.io.IOManager;
import com.models.Student;

import java.util.ArrayList;

public class StudentSaver implements ISaver<Student> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public void saveAll(ArrayList<Student> entities) {
        String stringEntities = this.gson.toJson(entities);
        this.ioManager.writeFile(FileNames.Students, stringEntities);
    }
}
