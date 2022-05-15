package com.models;

import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.models.interfaces.IStudent;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User implements IStudent {
    private UUID enrolledCourseId;
    private int level;
    private ArrayList<UUID> enrolledModules;
    private ArrayList<UUID> completedModules;

    public Student(UUID id, UserType userType, String forename, String surname, String email, String username, String password, UUID enrolledCourseId, int level, ArrayList<UUID> enrolledModules, ArrayList<UUID> completedModules) {
        super(id, userType, forename, surname, email, username, password);
        this.enrolledCourseId = enrolledCourseId;
        this.level = level;
        this.enrolledModules = enrolledModules;
        this.completedModules = completedModules;
    }

    public UUID getEnrolledCourseId() {
        return this.enrolledCourseId;
    }

    public void setEnrolledCourseId(UUID id) {
        this.enrolledCourseId = id;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<UUID> getEnrolledModules() {
        if (this.enrolledModules == null) {
            this.enrolledModules = new ArrayList<>();
        }
        return this.enrolledModules;
    }

    public void setEnrolledModules(ArrayList<UUID> enrolledModules) {
        this.enrolledModules = enrolledModules;
    }

    public void addEnrolledModule(UUID moduleId) {
        if (this.enrolledModules == null) {
            this.enrolledModules = new ArrayList<>();
        }

        this.enrolledModules.add(moduleId);
    }

    public void printEnrolledModules() {
        CourseLoader courseLoader = new CourseLoader();
        InstructorLoader instructorLoader = new InstructorLoader();
        Course enrolledCourse = courseLoader.find(this.enrolledCourseId);
        ArrayList<Module> courseModules = enrolledCourse.getModules();
        ArrayList<Module> enrolledModules = new ArrayList<>();
        for (UUID moduleID: this.getEnrolledModules()) {
            for (Module module: courseModules) {
                if (module.getId().equals(moduleID)) {
                    enrolledModules.add(module);
                }
            }
        }

        if (enrolledModules.size() == 0) {
            System.out.println("Oops there are no enrolled modules!");
            return;
        }

        AsciiTable moduleTable = new AsciiTable();
        moduleTable.addRule();
        moduleTable.addRow("Id", "Name", "Level", "Instructor");
        moduleTable.addRule();

        for (Module module: enrolledModules) {
            Instructor moduleInstructor = instructorLoader.find(module.getInstructor());
            moduleTable.addRow(module.getId(), module.getName(), module.getLevel(), moduleInstructor.getUsername());
            moduleTable.addRule();
        }

        System.out.println(moduleTable.render());
    }

    public ArrayList<UUID> getCompletedModules() {
        return this.completedModules;
    }

    public void setCompletedModules(ArrayList<UUID> completedModules) {
        this.completedModules = completedModules;
    }
}
