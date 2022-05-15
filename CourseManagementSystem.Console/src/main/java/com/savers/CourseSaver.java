package com.savers;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.io.IOManager;
import com.models.Course;

import java.util.ArrayList;

public class CourseSaver implements ISaver<Course> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public void saveAll(ArrayList<Course> entities) {
        String stringEntities = this.gson.toJson(entities);
        this.ioManager.writeFile(FileNames.Courses, stringEntities);
    }
}
