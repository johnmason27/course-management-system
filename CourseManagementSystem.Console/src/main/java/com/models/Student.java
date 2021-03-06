package com.models;

import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.models.interfaces.IStudent;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Student which can enroll onto courses and modules for that course and progress through levels
 * 4, 5, 6 before graduating.
 */
public class Student extends User implements IStudent {
    private UUID enrolledCourseId;
    private int level;
    private ArrayList<UUID> enrolledModules;
    private ArrayList<UUID> completedModules;
    private ArrayList<Grade> grades;

    /**
     * Initialize a Student.
     * @param id Student id
     * @param userType Student user type
     * @param forename Student forename
     * @param surname Student surname
     * @param email Student email
     * @param username Student username
     * @param password Student password
     * @param enrolledCourseId Id of course student enrolled on
     * @param level Level of student (4, 5, 6 and 7 for graduated)
     * @param enrolledModules Enrolled modules
     * @param completedModules Completed modules
     * @param grades Grades given from modules
     */
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
        return this.enrolledModules;
    }

    public ArrayList<Module> getEnrolledModulesWithDetails(CourseLoader courseLoader) {
        Course enrolledCourse = courseLoader.find(this.enrolledCourseId);
        ArrayList<Module> courseModules = enrolledCourse.getModules();
        ArrayList<Module> enrolledModules = new ArrayList<>();

        for (UUID id: this.getEnrolledModules()) {
            for (Module m: courseModules) {
                if (m.getId().equals(id)) {
                    enrolledModules.add(m);
                }
            }
        }

        return enrolledModules;
    }

    public void setEnrolledModules(ArrayList<UUID> enrolledModules) {
        this.enrolledModules = enrolledModules;
    }

    public void addEnrolledModule(UUID moduleId) {
        this.enrolledModules.add(moduleId);
    }

    public void removeEnrolledModule(UUID moduleId) {
        this.enrolledModules.remove(moduleId);
    }

    public void printEnrolledModules(CourseLoader courseLoader, InstructorLoader instructorLoader) {
        ArrayList<Module> enrolledModules = this.getEnrolledModulesWithDetails(courseLoader);

        if (enrolledModules.size() == 0) {
            System.out.println("Oops there are no enrolled modules!");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Name", "Level", "Instructor");
        table.addRule();

        for (Module m: enrolledModules) {
            Instructor moduleInstructor = instructorLoader.find(m.getInstructor());
            table.addRow(m.getId(), m.getName(), m.getLevel(), (moduleInstructor != null) ? moduleInstructor.getUsername() : "No instructor assigned");
            table.addRule();
        }

        System.out.println(table.render());
    }

    public ArrayList<UUID> getCompletedModules() {
        return this.completedModules;
    }

    public void setCompletedModules(ArrayList<UUID> completedModules) {
        this.completedModules = completedModules;
    }

    public void addCompletedModule(UUID moduleId) {
        this.completedModules.add(moduleId);
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

    public ArrayList<Module> getCompletedModulesWithDetails(CourseLoader courseLoader) {
        Course enrolledCourse = courseLoader.find(this.getEnrolledCourseId());
        ArrayList<Module> enrolledCourseModules = enrolledCourse.getModules();
        ArrayList<UUID> completedModuleIds = this.getCompletedModules();
        ArrayList<Module> completedModules = new ArrayList<>();

        for (Module e : enrolledCourseModules) {
            for (UUID c : completedModuleIds) {
                if (e.getId().equals(c)) {
                    completedModules.add(e);
                }
            }
        }

        return completedModules;
    }

    public ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader) {
        ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = new ArrayList<>();
        ArrayList<Grade> grades = this.getGrades();

        for (Module m: this.getCompletedModulesWithDetails(courseLoader)) {
            for (Grade g: grades) {
                if (m.getId().equals(g.getModuleId())) {
                    int grade = g.getGrade();
                    if (grade >= 40) {
                        completedModulesWithGrade.add(new CompletedModuleWithGrade(m, g));
                    }
                }
            }
        }

        return completedModulesWithGrade;
    }

    public ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader, int level) {
        ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = new ArrayList<>();
        ArrayList<Grade> grades = this.getGrades();

        for (Module m: this.getCompletedModulesWithDetails(courseLoader)) {
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
