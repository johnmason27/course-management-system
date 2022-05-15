package com.printers;

import com.loaders.StudentLoader;
import com.models.Student;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class StudentPrinter {
    private static final StudentLoader studentLoader = new StudentLoader();
    public static void printStudentsOnModule(UUID id) {
        ArrayList<Student> studentsOnModule = new ArrayList<>();
        for (Student student : studentLoader.loadAll()) {
            if (student.getEnrolledModules().stream()
                    .filter(m -> m.equals(id))
                    .findAny()
                    .orElse(null) != null) {
                studentsOnModule.add(student);
            }
        }

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
}
