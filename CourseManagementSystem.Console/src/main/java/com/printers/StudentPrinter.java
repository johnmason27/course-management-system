package com.printers;

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

    public static void printAllStudents(ArrayList<Student> students) {
        if (students.size() == 0) {
            System.out.println("Oh looks like there are no students!");
            return;
        }

        AsciiTable studentsTable = new AsciiTable();
        studentsTable.addRule();
        studentsTable.addRow("Id", "Username");
        studentsTable.addRule();

        for (Student s : students) {
            studentsTable.addRow(s.getId(), s.getUsername());
            studentsTable.addRule();
        }

        System.out.println(studentsTable.render());
    }

    public static void printStudentsOnModule(UUID id) {
        ArrayList<Student> studentsOnModule = studentLoader.loadFromModule(id);

        if (studentsOnModule.size() == 0) {
            System.out.println("Oh looks like there are no students on the module.");
            return;
        }

        AsciiTable studentsTable = new AsciiTable();
        studentsTable.addRule();
        studentsTable.addRow("Id", "Username");
        studentsTable.addRule();

        for (Student student : studentsOnModule) {
            studentsTable.addRow(student.getId(), student.getUsername());
            studentsTable.addRule();
        }

        System.out.println(studentsTable.render());
    }

    public static void printCompletedModulesWithGrade(ArrayList<CompletedModuleWithGrade> completedModuleWithGrades) {
        if (completedModuleWithGrades.size() == 0) {
            System.out.println("No completed modules with grades!");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Name", "Level", "Grade");
        table.addRule();

        for (CompletedModuleWithGrade c : completedModuleWithGrades) {
            table.addRow(c.getModule().getName(), c.getModule().getLevel(), String.format("%d%%", c.getGrade().getGrade()));
            table.addRule();
        }

        System.out.println(table.render());
    }
}
