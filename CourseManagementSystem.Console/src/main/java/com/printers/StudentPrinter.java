package com.printers;

import com.loaders.CourseLoader;
import com.loaders.StudentLoader;
import com.models.CompletedModuleWithGrade;
import com.models.Student;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Print student information to the console in ASCII tables.
 */
public class StudentPrinter {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final CourseLoader courseLoader = new CourseLoader();

    /**
     * Print all the given students in an ASCII table.
     * @param students The students to print
     */
    public static void printAllStudents(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("Oh looks like there are no students!");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Username");
        table.addRule();

        for (Student s : students) {
            table.addRow(s.getId(), s.getUsername());
            table.addRule();
        }

        System.out.println(table.render());
    }

    /**
     * Print all the students in an ASCII table on a given module.
     * @param id Id of the module to search
     */
    public static void printStudentsOnModule(UUID id) {
        ArrayList<Student> studentsOnModule = studentLoader.loadFromModule(id);

        if (studentsOnModule.size() == 0) {
            System.out.println("Oh looks like there are no students on the module.");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Username");
        table.addRule();

        for (Student s : studentsOnModule) {
            table.addRow(s.getId(), s.getUsername());
            table.addRule();
        }

        System.out.println(table.render());
    }

    /**
     * Print all the given completed modules with a grade out into an ASCII table.
     * @param completedModuleWithGrades The completed modules with grades
     */
    public static void printCompletedModulesWithGrade(ArrayList<CompletedModuleWithGrade> completedModuleWithGrades) {
        if (completedModuleWithGrades.size() == 0) {
            System.out.println("No completed modules with grades!");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Name", "Level", "Grade", "Optional");
        table.addRule();

        for (CompletedModuleWithGrade c : completedModuleWithGrades) {
            table.addRow(c.getModule().getName(), c.getModule().getLevel(), String.format("%d%%", c.getGrade().getGrade()), c.getModule().getOptional() ? "Optional" : "Required");
            table.addRule();
        }

        System.out.println(table.render());
    }

    /**
     * Print the report for a given student containing their Name, Email, Level and
     * a table containing their completed modules.
     * @param student The student to compile the report for
     * @return The report
     */
    public static String printReport(Student student) {
        ArrayList<CompletedModuleWithGrade> completedModuleWithGrades = student.getCompletedModulesWithGrade(courseLoader);

        if (completedModuleWithGrades.size() == 0) {
            System.out.println("No completed modules! Nothing to print.");
            return null;
        }

        String studentName = student.getForename() + " " + student.getSurname();
        String studentEmail = student.getEmail();
        int studentLevel = student.getLevel();
        String renderString = String.format("Name: %s%nEmail: %s%nLevel: %s%n", studentName, studentEmail, studentLevel == 7 ? "Graduated" : studentLevel);

        String completedModules = "Completed Modules:\n";
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Module", "Level", "Grade");
        table.addRule();

        for (CompletedModuleWithGrade c: completedModuleWithGrades) {
            table.addRow(c.getModule().getName(), c.getModule().getLevel(), String.format("%d%%", c.getGrade().getGrade()));
            table.addRule();
        }

        return renderString + getStudentLevelUpString(student) + completedModules + table.render();
    }

    private static String getStudentLevelUpString(Student student) {
        String levelUpString = null;
        int level = student.getLevel();

        if (level == 4) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(courseLoader, 4);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            if (completedModulesWithGrade.size() < 4) {
                levelUpString = String.format("%s hasn't passed at least 4, level 4 modules yet. They cannot progress to the next level.%n", student.getUsername());
            } else {
                levelUpString = String.format("%s has passed enough modules to be level 5!%n", student.getUsername());
            }
        } else if (level == 5) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(courseLoader, 5);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            if (completedModulesWithGrade.size() < 4) {
                levelUpString = String.format("%s hasn't passed at least 4, level 5 modules yet. They cannot progress to the next level.%n", student.getUsername());
            } else {
                levelUpString = String.format("%s has passed enough modules to be level 6!%n", student.getUsername());
            }
        } else if (level == 6) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(courseLoader, 6);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            int optionalModuleCount = 0;
            int requiredModuleCount = 0;
            for (CompletedModuleWithGrade c: completedModulesWithGrade) {
                if (c.getModule().getOptional()) {
                    optionalModuleCount += 1;
                } else {
                    requiredModuleCount += 1;
                }
            }

            if (optionalModuleCount < 2 || requiredModuleCount < 2) {
                levelUpString = String.format("%s hasn't passed at least 2 optional and 2 required, level 6 modules yet. They cannot graduate.%n", student.getUsername());
            } else {
                levelUpString = String.format("%s has passed enough modules to graduate!%n", student.getUsername());
            }
        }

        return levelUpString;
    }
}
