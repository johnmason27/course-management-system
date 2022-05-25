package com.printers;

import com.loaders.InstructorLoader;
import com.models.Instructor;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

/**
 * Print all the instructor information in ASCII tables.
 */
public class InstructorPrinter {
    private static final InstructorLoader instructorLoader = new InstructorLoader();

    /**
     * Print all given instructors into an ASCII table.
     * @param instructors Instructors to print
     */
    public static void printInstructors(ArrayList<Instructor> instructors) {
        if (instructors.size() == 0) {
            System.out.println("Oh looks like there is no instructors.");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Name");
        table.addRule();

        for (Instructor i : instructors) {
            Instructor instructorUser = instructorLoader.find(i.getId());

            table.addRow(instructorUser.getId(), String.format("%s %s", instructorUser.getForename(), instructorUser.getSurname()));
            table.addRule();
        }

        System.out.println(table.render());
    }
}
