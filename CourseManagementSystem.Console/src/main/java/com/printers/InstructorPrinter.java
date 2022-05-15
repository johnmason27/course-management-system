package com.printers;

import com.loaders.InstructorLoader;
import com.models.Instructor;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

public class InstructorPrinter {
    private static final InstructorLoader instructorLoader = new InstructorLoader();

    public static void printInstructors(ArrayList<Instructor> instructors) {
        if (instructors.size() == 0) {
            System.out.println("Oh looks like there is no instructors.");
            return;
        }

        AsciiTable instructorsTable = new AsciiTable();
        instructorsTable.addRule();
        instructorsTable.addRow("Id", "Name");
        instructorsTable.addRule();

        for (Instructor instructor : instructors) {
            Instructor instructorUser = instructorLoader.find(instructor.getId());

            instructorsTable.addRow(instructorUser.getId(), String.format("%s %s", instructorUser.getForename(), instructorUser.getSurname()));
            instructorsTable.addRule();
        }

        System.out.println(instructorsTable.render());
    }
}
