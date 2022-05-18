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
    private ArrayList<Grade> grades;

    public Student(UUID id, UserType userType, String forename, String surname, String email, String username, String password, UUID enrolledCourseId, int level, ArrayList<UUID> enrolledModules, ArrayList<UUID> completedModules, ArrayList<Grade> grades) {
        super(id, userType, forename, surname, email, username, password);
        this.enrolledCourseId = enrolledCourseId;
        this.level = level;
        this.enrolledModules = enrolledModules;
        this.completedModules = completedModules;
        this.grades = grades;
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

    public ArrayList<Module> getEnrolledModulesWithDetails() {
        CourseLoader courseLoader = new CourseLoader();
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

        return enrolledModules;
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
        InstructorLoader instructorLoader = new InstructorLoader();
        ArrayList<Module> enrolledModules = this.getEnrolledModulesWithDetails();

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
            moduleTable.addRow(module.getId(), module.getName(), module.getLevel(), (moduleInstructor != null) ? moduleInstructor.getUsername() : "No instructor assigned");
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

    public ArrayList<Grade> getGrades() {
        return this.grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public ArrayList<Module> getCompletedModulesWithDetails() {
        CourseLoader courseLoader = new CourseLoader();
        Course enrolledCourse = courseLoader.find(this.getEnrolledCourseId());
        ArrayList<Module> enrolledCourseModules = enrolledCourse.getModules();
        ArrayList<UUID> completedModuleIds = this.getCompletedModules();
        ArrayList<Module> completedModules = new ArrayList<>();

        for (Module enrolledCourseModule : enrolledCourseModules) {
            for (UUID completedModuleId : completedModuleIds) {
                if (enrolledCourseModule.getId().equals(completedModuleId)) {
                    completedModules.add(enrolledCourseModule);
                }
            }
        }

        return completedModules;
    }

    public ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGradeForLevel(int level) {
        ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = new ArrayList<>();
        ArrayList<Grade> grades = this.getGrades();

        for (Module m: this.getCompletedModulesWithDetails()) {
            for (Grade g: grades) {
                if (m.getLevel() == level && m.getId().equals(g.getModuleId())) {
                    int grade = g.getGrade();
                    if (grade >= 40) {
                        completedModulesWithGrade.add(new CompletedModuleWithGrade(m, g));
                    }
                }
            }
        }

        return completedModulesWithGrade;
    }
}
