package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import de.vandermeer.asciitable.AsciiTable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Instructors {
    private static IOManager ioManager;
    private static ArrayList<Instructor> instructors;

    public Instructors() {
        ioManager = new IOManager();
        String instructorsFileContents = ioManager.readFile(FileNames.Instructors);
        Gson gson = new Gson();
        Type instructorListType = new TypeToken<ArrayList<Instructor>>(){}.getType();
        instructors = gson.fromJson(instructorsFileContents, instructorListType);
    }

    public static void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        saveInstructors();
    }

    public static ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public static ArrayList<Instructor> getAvailableInstructors() {
        List<Instructor> availableInstructors = instructors.stream()
                .filter(instructor -> instructor.getAssignedModules().size() < 4)
                .toList();
        return new ArrayList<>(availableInstructors);
    }

    public static Instructor getInstructor(UUID id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public static void updateInstructor(Instructor instructor) {
        int index = 0;
        UUID instructorId = instructor.getId();

        for (int i = 0; i < instructors.size(); i ++) {
            if (instructors.get(i).getId().equals(instructorId)) {
                index = i;
                break;
            }
        }

        instructors.set(index, instructor);
        saveInstructors();
    }

    public static ArrayList<Module> getAssignedModules(UUID instructorId) {
        ArrayList<Module> assignedModules = new ArrayList<>();

        for (Course course :
                Courses.getCourses()) {
            ArrayList<Module> courseModules = course.getModules();
            for (Module module :
                    courseModules) {
                if (module.getInstructor() != null && module.getInstructor().equals((instructorId))) {
                    assignedModules.add(module);
                }
            }
        }

        return assignedModules;
    }

    public static void printInstructors(ArrayList<Instructor> instructors) {
        if (instructors.size() == 0) {
            System.out.println("Oh looks like there is no instructors.");
            return;
        }

        AsciiTable instructorsTable = new AsciiTable();
        instructorsTable.addRule();
        instructorsTable.addRow("Id", "Name");
        instructorsTable.addRule();

        for (Instructor instructor :
                instructors) {
            UUID id = instructor.getId();
            User instructorUser = Users.findInstructor(id);

            instructorsTable.addRow(id, String.format("%s %s", instructorUser.getForename(), instructorUser.getSurname()));
            instructorsTable.addRule();
        }

        System.out.println(instructorsTable.render());
    }

    private static void saveInstructors() {
        Gson gson = new Gson();
        String instructorsJson = gson.toJson(instructors);
        ioManager.writeFile(FileNames.Instructors, instructorsJson);
    }
}
