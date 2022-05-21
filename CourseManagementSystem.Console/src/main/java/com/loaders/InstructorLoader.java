package com.loaders;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import com.models.Instructor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstructorLoader implements ILoader<Instructor> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public ArrayList<Instructor> loadAll() {
        String instructorsFileContents = this.ioManager.readFile(FileNames.INSTRUCTORS);
        Type instructorListType = new TypeToken<ArrayList<Instructor>>(){}.getType();
        ArrayList<Instructor> instructors = this.gson.fromJson(instructorsFileContents, instructorListType);

        if (instructors == null) {
            return new ArrayList<>();
        }

        return instructors;
    }

    public Instructor find(String nameEmail) {
        return this.loadAll().stream()
                .filter(i -> i.getUsername().equals(nameEmail) || i.getEmail().equals(nameEmail))
                .findAny()
                .orElse(null);
    }

    public Instructor find(UUID id) {
        return this.loadAll().stream()
                .filter(i -> i.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public ArrayList<Instructor> findAvailable() {
        List<Instructor> availableInstructors = this.loadAll().stream()
                .filter(i -> i.getAssignedModules().size() < 4)
                .toList();
        return new ArrayList<>(availableInstructors);
    }
}
