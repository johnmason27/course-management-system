package com.loaders;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import com.models.Student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentLoader implements ILoader<Student> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public ArrayList<Student> loadAll() {
        String studentsFileContents = this.ioManager.readFile(FileNames.STUDENTS);
        Type studentListType = new TypeToken<ArrayList<Student>>(){}.getType();
        ArrayList<Student> students = this.gson.fromJson(studentsFileContents, studentListType);

        if (students == null) {
            return new ArrayList<>();
        }

        return students;
    }

    public ArrayList<Student> loadFromModule(UUID moduleId) {
        ArrayList<Student> studentsOnModule = new ArrayList<>();

        for (Student s : this.loadAll()) {
            if (s.getEnrolledModules().stream()
                    .filter(m -> m.equals(moduleId))
                    .findAny()
                    .orElse(null) != null) {
                studentsOnModule.add(s);
            }
        }

        return studentsOnModule;
    }

    public ArrayList<Student> loadPreGraduates() {
        List<Student> filteredStudentList = this.loadAll().stream()
                .filter(s -> s.getLevel() == 4 || s.getLevel() == 5 || s.getLevel() == 6)
                .toList();

        return new ArrayList<>(filteredStudentList);
    }

    public Student find(String nameEmail) {
        return this.loadAll().stream()
                .filter(s -> s.getUsername().equals(nameEmail) || s.getEmail().equals(nameEmail))
                .findAny()
                .orElse(null);
    }

    public Student find(UUID id) {
        return this.loadAll().stream()
                .filter(s -> s.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
