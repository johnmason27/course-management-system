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

    public static void printGrades(Student student) {
        ArrayList<Module> enrolledModules = student.getEnrolledModulesWithDetails();
        ArrayList<Grade> grades = student.getGrades();

        AsciiTable gradesTable = new AsciiTable();
        gradesTable.addRule();
        gradesTable.addRow("Module", "Grade");
        gradesTable.addRule();

        for (Grade grade : grades) {
            for (Module enrolledModule: enrolledModules) {
                if (grade.getModuleId().equals(enrolledModule.getId())) {
                    gradesTable.addRow(enrolledModule.getName(), String.format("%d%%", grade.getGrade()));
                    gradesTable.addRule();
                }
            }
        }

        System.out.println(gradesTable.render());
    }

    public static void printCompletedModulesWithGrade(ArrayList<CompletedModuleWithGrade> completedModuleWithGrades) {
        if (completedModuleWithGrades.size() == 0) {
            System.out.println("No completed modules with grades!");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Completed modules with grades");
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
