package com.printers;

import com.loaders.CourseLoader;
import com.loaders.StudentLoader;
import com.models.CompletedModuleWithGrade;
import com.models.Grade;
import com.models.Module;
import com.models.Student;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class StudentPrinter {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final CourseLoader courseLoader = new CourseLoader();

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

        for (Student student : studentsOnModule) {
            table.addRow(student.getId(), student.getUsername());
            table.addRule();
        }

        System.out.println(table.render());
    }

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

        return renderString + completedModules + table.render();
    }
}
